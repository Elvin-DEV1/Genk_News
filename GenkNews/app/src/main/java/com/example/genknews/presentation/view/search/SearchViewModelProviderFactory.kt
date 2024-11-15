package com.example.genknews.presentation.view.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genknews.control.repository.NewsSearchRepository

class SearchViewModelProviderFactory(
    val app: Application,
    val searchRepository: NewsSearchRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return SearchViewModel(app, searchRepository) as T
    }
}