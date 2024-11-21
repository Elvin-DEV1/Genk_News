package com.example.genknews.presentation.view.menu

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AbsListView
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.genknews.R
import com.example.genknews.common.utils.Constants
import com.example.genknews.common.utils.Resource
import com.example.genknews.databinding.FragmentMenuBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.adapters.CategoryAdapter
import com.example.genknews.presentation.view.home.HomeViewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {
    lateinit var menuViewModel: HomeViewModel
    lateinit var categoryAdapter: CategoryAdapter
    private lateinit var retryButton: Button
    private lateinit var errorText: TextView
    lateinit var searchEdit: EditText
    private lateinit var itemMenuError: CardView
    lateinit var binding: FragmentMenuBinding
    private var lastSearchQuery = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMenuBinding.bind(view)

        itemMenuError = view.findViewById(R.id.itemSearchError)
        val inflater =
            requireContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.item_error, null)

        retryButton = view.findViewById(R.id.retryButton)
        errorText = view.findViewById(R.id.errorText)
        searchEdit = binding.searchEdit

        menuViewModel = (activity as MainActivity).homeViewModel
        setupMenuRecycler()

        binding.searchEdit.addTextChangedListener { editable ->
            editable?.let {
                val currentQuery = it.toString()
                if (currentQuery.isNotEmpty() && currentQuery != lastSearchQuery) {
                    lastSearchQuery = currentQuery
                    navigateToSearch(currentQuery)
                }
            }
        }

        binding.searchEdit.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                navigateToSearch(binding.searchEdit.text.toString())
                true
            } else {
                false
            }
        }

        categoryAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("category", it)
            }
            findNavController().navigate(R.id.action_menuFragment_to_categoryFragment, bundle)
        }

        menuViewModel.getCategory()

        menuViewModel.menu.observe(viewLifecycleOwner) { response ->
            Log.d("MenuFragment", "Response: $response")
            when (response) {
                is Resource.Success<*> -> {
                    hideProgressBar()
                    hideErrorMessage()
                    response.data?.let { categoryResponse ->
                        categoryAdapter.differ.submitList(categoryResponse.categoriesBox.distinctBy { it.id })
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
            menuViewModel.getCategory()
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
        itemMenuError.visibility = View.INVISIBLE
        isError = false
    }

    private fun showErrorMessage(message: String) {
        itemMenuError.visibility = View.VISIBLE
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
                menuViewModel.getCategory()
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

    private fun navigateToSearch(searchQuery: String) {
        val bundle = Bundle().apply {
            putString("search_query", searchQuery)
        }
        findNavController().navigate(
            R.id.action_menuFragment_to_searchFragment,
            bundle
        )
    }

    private fun setupMenuRecycler() {
        categoryAdapter = CategoryAdapter()
        binding.recyclerCategory.apply {
            adapter = categoryAdapter
            layoutManager = GridLayoutManager(context, 2)
            addOnScrollListener(this@MenuFragment.scrollListener)
        }
    }
}