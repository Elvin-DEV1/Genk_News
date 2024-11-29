package com.example.genknews.presentation.view.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.genknews.R
import com.example.genknews.common.utils.Constants
import com.example.genknews.common.utils.Resource
import com.example.genknews.databinding.FragmentHomeBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.adapters.NewsHomeAdapter

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var homeViewModel: HomeViewModel
    private lateinit var newsHomeAdapter: NewsHomeAdapter
    private lateinit var retryButton: Button
    private lateinit var errorText: TextView
    private lateinit var itemHomeError: CardView
    lateinit var binding: FragmentHomeBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        itemHomeError = view.findViewById(R.id.itemHomeError)
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_error, null)

        retryButton = view.findViewById(R.id.retryButton)
        errorText = view.findViewById(R.id.errorText)

        homeViewModel = (activity as MainActivity).homeViewModel
        setupHomeRecycler()

        newsHomeAdapter.setOnItemClickListener { newsItem ->
            if (newsItem.url.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString("url", newsItem.url)
                }

                try {
                    findNavController().navigate(R.id.action_homeFragment_to_articleFragment, bundle)
                } catch (e: Exception) {
                    Log.e("HomeFragment", "Navigation error: ${e.message}")
                    Toast.makeText(requireContext(), "Không thể mở bài viết", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "Bài viết không khả dụng", Toast.LENGTH_SHORT).show()
            }
        }

        newsHomeAdapter.setOnRelatedNewsClickListener {
            val bundle = Bundle().apply {
                putString("urlRelation", it.url)
            }
            findNavController().navigate(R.id.action_homeFragment_to_articleFragment, bundle)
        }

        homeViewModel.getHome()

        homeViewModel.home.observe(viewLifecycleOwner) { response ->
            Log.d("HomeFragment", "Response: $response")
            when (response) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { newsHomeResponse ->
                        val distinctList = newsHomeResponse.homeNewsPosition.data.toList()
                            .distinctBy { it.newsId }
                        newsHomeAdapter.differ.submitList(distinctList)
                        binding.recyclerHome.setPadding(0, 0, 0, 0)
                    }
                }

                is Resource.Error<*> -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "Error: $message", Toast.LENGTH_SHORT).show()
                        showErrorMessage(message)
                    }
                }

                is Resource.Loading<*> -> {
                    showProgressBar()
                }
            }
        }
        retryButton.setOnClickListener {
            homeViewModel.getHome()
        }
    }

    var isError = false
    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private fun hideProgressBar() {
        binding.paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun showProgressBar() {
        binding.paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    private fun hideErrorMessage() {
        itemHomeError.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        itemHomeError.visibility = View.VISIBLE
        errorText.text = message
        isError = true
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNoErrors = !isError
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= Constants.QUERY_PAGE_SIZE
            val shouldPaginate =
                isNoErrors && isNotLoadingAndNotLastPage && isAtLastItem
                        && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                homeViewModel.getHome()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupHomeRecycler() {
        newsHomeAdapter = NewsHomeAdapter()
        binding.recyclerHome.apply {
            adapter = newsHomeAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@HomeFragment.scrollListener)
        }
    }
}
