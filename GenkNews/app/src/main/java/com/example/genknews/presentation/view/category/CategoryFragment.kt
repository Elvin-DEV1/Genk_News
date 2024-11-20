package com.example.genknews.presentation.view.category

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AbsListView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.genknews.R
import com.example.genknews.common.entity.Category
import com.example.genknews.common.utils.Constants
import com.example.genknews.common.utils.Resource
import com.example.genknews.databinding.FragmentCategoryBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.adapters.NewsCategoryAdapter
import com.example.genknews.presentation.view.home.HomeViewModel

class CategoryFragment : Fragment(R.layout.fragment_category) {

    private lateinit var categoryViewModel: HomeViewModel
    private lateinit var newsCategoryAdapter: NewsCategoryAdapter
    private lateinit var imgLoading: ImageView
    private lateinit var retryButton: Button
    private lateinit var backButton: ImageButton
    private lateinit var errorText: TextView
    private lateinit var itemCategoryError: CardView
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var category: Category

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoryBinding.bind(view)

        // Get category from arguments
        category = arguments?.getSerializable("category") as Category

        // Setup error view
        itemCategoryError = view.findViewById(R.id.itemCategoryError)
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val errorView: View = inflater.inflate(R.layout.item_error, null)

        retryButton = errorView.findViewById(R.id.retryButton)
        errorText = errorView.findViewById(R.id.errorText)
        backButton = view.findViewById(R.id.btnBack)

        // Set category title
        binding.tvCategoryName.text = category.name

        // Initialize ViewModel and RecyclerView
        categoryViewModel = (activity as MainActivity).homeViewModel
        setupCategoryRecycler()

        // Handle item click
        newsCategoryAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("newsCategory", it)
            }
            findNavController().navigate(R.id.action_categoryFragment_to_articleFragment, bundle)
        }

        categoryViewModel.getNewsCategory(category.id.toString())

        categoryViewModel.category.observe(viewLifecycleOwner) { response ->
            Log.d("CategoryFragment", "Response: $response")
            when (response) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { newsCategoryResponse ->
                        newsCategoryAdapter.differ.submitList(newsCategoryResponse.news)
                        binding.recyclerCategory.setPadding(0, 0, 0, 0)
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
            categoryViewModel.getNewsCategory(category.id.toString())
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
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
        itemCategoryError.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        itemCategoryError.visibility = View.VISIBLE
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
                isNoErrors && isNotLoadingAndNotLastPage && isAtLastItem &&
                        isNotAtBeginning && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                categoryViewModel.getNewsCategory(category.id.toString())
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

    private fun setupCategoryRecycler() {
        newsCategoryAdapter = NewsCategoryAdapter()
        binding.recyclerCategory.apply {
            adapter = newsCategoryAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@CategoryFragment.scrollListener)
        }
    }
}