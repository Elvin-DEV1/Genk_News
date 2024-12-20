package com.example.genknews.presentation.view.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.AbsListView
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.genknews.R
import com.example.genknews.common.utils.Constants
import com.example.genknews.common.utils.Resource
import com.example.genknews.databinding.FragmentSearchBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.adapters.NewsSearchAdapter
import com.example.genknews.presentation.view.home.HomeViewModel

class SearchFragment : Fragment(R.layout.fragment_search) {

    lateinit var searchViewModel: HomeViewModel
    private lateinit var newsAdapter: NewsSearchAdapter
    private lateinit var retryButton: Button
    private lateinit var errorText: TextView
    private lateinit var backButton: ImageButton
    lateinit var itemSearchError: CardView
    lateinit var binding: FragmentSearchBinding
    private var lastSearchQuery = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)

        itemSearchError = view.findViewById(R.id.itemSearchError)
        retryButton = view.findViewById(R.id.retryButton)
        errorText = view.findViewById(R.id.errorText)
        backButton = binding.btnBack

        searchViewModel = (activity as MainActivity).homeViewModel
        setupSearchRecycler()

        val searchQuery = arguments?.getString("search_query") ?: ""
        binding.searchEdit.setText(searchQuery)

        if (searchQuery.isNotEmpty()) {
            performSearch(searchQuery)
        }

        binding.searchEdit.addTextChangedListener { editable ->
            val currentQuery = editable.toString().trim()
            if (currentQuery != lastSearchQuery) {
                lastSearchQuery = currentQuery
                if (currentQuery.isNotEmpty()) {
                    performSearch(currentQuery)
                } else {
                    clearSearchResults()
                }
            }
        }

        binding.searchEdit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                val query = binding.searchEdit.text.toString().trim()
                if (query.isNotEmpty()) {
                    performSearch(query)
                    val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(binding.searchEdit.windowToken, 0)
                    return@setOnEditorActionListener true
                }
            }
            false
        }

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putString("url", it.url)
            }
            findNavController().navigate(R.id.action_searchFragment_to_articleFragment, bundle)
        }

        backButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.tvCancel.setOnClickListener {
            findNavController().navigateUp()
        }

        retryButton.setOnClickListener {
            val query = binding.searchEdit.text.toString()
            if (query.isNotEmpty()) {
                performSearch(query)
            } else {
                hideErrorMessage()
            }
        }
    }

    private fun performSearch(query: String) {
        searchViewModel.getSearch(query, "1", "20")
        observeSearchResults(query)
    }

    private fun observeSearchResults(searchQuery: String) {
        searchViewModel.search.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { newsSearchResponse ->
                        val filteredList = newsSearchResponse.news.toList()
                            .distinctBy { it.title }
                            .filter { it.title.contains(searchQuery, ignoreCase = true) }

                        newsAdapter.differ.submitList(filteredList)
                        binding.rvSearchResults.setPadding(0, 0, 0, 0)
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
    }

    private fun clearSearchResults() {
        newsAdapter.differ.submitList(emptyList())
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
        itemSearchError.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        itemSearchError.visibility = View.VISIBLE
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
                searchViewModel.getSearch(binding.searchEdit.text.toString())
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

    private fun setupSearchRecycler() {
        newsAdapter = NewsSearchAdapter()
        binding.rvSearchResults.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@SearchFragment.scrollListener)
        }
    }
}
