package com.example.genknews.presentation.view.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genknews.control.repository.NewsHomeRepository

class HomeViewModelProviderFactory(
    val app: Application,
    val newsHomeRepository: NewsHomeRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeViewModel(app, newsHomeRepository) as T
    }
}