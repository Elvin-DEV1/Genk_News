package com.example.genknews.presentation.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.genknews.R
import com.example.genknews.databinding.FragmentArticleBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.view.home.HomeViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var newsViewModel: HomeViewModel
    val args: ArticleFragmentArgs by navArgs()
    lateinit var binding: FragmentArticleBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        newsViewModel = (activity as MainActivity).homeViewModel
        val news = args.news

        binding.webView.apply {
            webViewClient = WebViewClient()
            loadUrl(news.url)
        }
    }
}