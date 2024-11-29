package com.example.genknews.presentation.view.latest

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.ImageView
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
import com.example.genknews.databinding.FragmentLatestBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.adapters.NewsLatestAdapter
import com.example.genknews.presentation.view.home.HomeViewModel

class LatestFragment : Fragment(R.layout.fragment_latest) {

    lateinit var latestViewModel: HomeViewModel
    private lateinit var newsLatestAdapter: NewsLatestAdapter
    lateinit var imgLoading: ImageView
    private lateinit var retryButton: Button
    private lateinit var errorText: TextView
    private lateinit var itemLatestError: CardView
    lateinit var binding: FragmentLatestBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLatestBinding.bind(view)

        itemLatestError = view.findViewById(R.id.itemLatestError)
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_error, null)

        retryButton = view.findViewById(R.id.retryButton)
        errorText = view.findViewById(R.id.errorText)

        latestViewModel = (activity as MainActivity).homeViewModel
        setupLatestRecycler()

        newsLatestAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("url", it.url)
            }
            findNavController().navigate(R.id.action_latestFragment_to_articleFragment, bundle)
        }

        newsLatestAdapter.setOnRelatedNewsClickListener {
            val bundle = Bundle().apply {
                putString("urlRelation", it.url)
            }
            findNavController().navigate(R.id.action_latestFragment_to_articleFragment, bundle)
        }

        latestViewModel.getLatest()

        latestViewModel.latest.observe(viewLifecycleOwner) { response ->
            Log.d("LatestFragment", "Response: $response")
            when (response) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { newsLatestResponse ->
                        newsLatestAdapter.differ.submitList(
                            newsLatestResponse.news.toList().distinctBy { it.newsId })
                        binding.recyclerLatest.setPadding(0, 0, 0, 0)
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
            latestViewModel.getLatest()
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
        itemLatestError.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        itemLatestError.visibility = View.VISIBLE
        errorText.text = message
        isError = true
    }

    val scrollListener = object : RecyclerView.OnScrollListener() {
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
                latestViewModel.getLatest()
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

    private fun setupLatestRecycler() {
        newsLatestAdapter = NewsLatestAdapter()
        binding.recyclerLatest.apply {
            adapter = newsLatestAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@LatestFragment.scrollListener)
        }
    }
}