package com.example.genknews.presentation.view.menu

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.genknews.control.repository.CategoryRepository

class MenuViewModelProviderFactory(
    val app: Application,
    val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MenuViewModel(app, categoryRepository) as T
    }
}