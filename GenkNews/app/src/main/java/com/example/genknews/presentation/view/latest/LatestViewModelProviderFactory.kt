package com.example.genknews.presentation.view.latest

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genknews.control.repository.NewsLatestRepository

class LatestViewModelProviderFactory(
    val app: Application,
    val newsLatestRepository: NewsLatestRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LatestViewModel(app, newsLatestRepository) as T
    }
}