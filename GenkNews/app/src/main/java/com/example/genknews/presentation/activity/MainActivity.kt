package com.example.genknews.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.genknews.R
import com.example.genknews.control.repository.CategoryRepository
import com.example.genknews.control.repository.NewsCategoryRepository
import com.example.genknews.control.repository.NewsHomeRepository
import com.example.genknews.control.repository.NewsLatestRepository
import com.example.genknews.control.repository.NewsSearchRepository
import com.example.genknews.databinding.ActivityMainBinding
import com.example.genknews.presentation.view.home.HomeViewModel
import com.example.genknews.presentation.view.home.HomeViewModelProviderFactory

class MainActivity : AppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val newsHomeRepository = NewsHomeRepository()
        val newsCategoryRepository = NewsCategoryRepository()
        val newsLatestRepository = NewsLatestRepository()
        val categoryRepository = CategoryRepository()
        val newsSearchRepository = NewsSearchRepository()
        val homeViewModelProviderFactory =
            HomeViewModelProviderFactory(
                application,
                newsHomeRepository,
                newsCategoryRepository,
                newsLatestRepository,
                categoryRepository,
                newsSearchRepository
            )
        homeViewModel =
            ViewModelProvider(this, homeViewModelProviderFactory)[HomeViewModel::class.java]

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newsNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}