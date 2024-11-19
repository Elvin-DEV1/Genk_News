package com.example.genknews.presentation.view.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genknews.control.repository.CategoryRepository
import com.example.genknews.control.repository.NewsCategoryRepository
import com.example.genknews.control.repository.NewsHomeRepository
import com.example.genknews.control.repository.NewsLatestRepository
import com.example.genknews.control.repository.NewsSearchRepository

class HomeViewModelProviderFactory(
    val app: Application,
    private val newsHomeRepository: NewsHomeRepository,
    private val newsCategoryRepository: NewsCategoryRepository,
    private val newsLatestRepository: NewsLatestRepository,
    private val categoryRepository: CategoryRepository,
    private val newsSearchRepository: NewsSearchRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(
            app,
            newsHomeRepository,
            newsCategoryRepository,
            newsLatestRepository,
            categoryRepository,
            newsSearchRepository
        ) as T
    }
}