package com.example.genknews.presentation.view.category

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genknews.control.repository.NewsCategoryRepository

class CategoryViewModelProviderFactory(
    val app: Application,
    val newsCategoryRepository: NewsCategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(app, newsCategoryRepository) as T
    }
}