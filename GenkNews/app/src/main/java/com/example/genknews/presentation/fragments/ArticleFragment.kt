package com.example.genknews.presentation.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.genknews.R
import com.example.genknews.common.utils.Constants
import com.example.genknews.databinding.FragmentArticleBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.view.home.HomeViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    lateinit var newsViewModel: HomeViewModel
    lateinit var binding: FragmentArticleBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)

        newsViewModel = (activity as MainActivity).homeViewModel

        val newsUrl = arguments?.getString("url")

        if (newsUrl.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "URL không hợp lệ", Toast.LENGTH_SHORT).show()
            return
        }

        binding.webView.apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                loadWithOverviewMode = true
                useWideViewPort = true
                builtInZoomControls = true
                displayZoomControls = false
            }

            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Log.d("ArticleFragment", "Page loaded: $url")
                }

                override fun shouldOverrideUrlLoading(
                    view: WebView?,
                    request: WebResourceRequest?
                ): Boolean {
                    view?.loadUrl(request?.url.toString())
                    return true
                }

                @Deprecated("Deprecated in Java")
                override fun onReceivedError(
                    view: WebView?,
                    errorCode: Int,
                    description: String?,
                    failingUrl: String?
                ) {
                    super.onReceivedError(view, errorCode, description, failingUrl)
                    Log.e("ArticleFragment", "Error loading URL: $failingUrl")
                    Toast.makeText(
                        requireContext(),
                        "Không thể tải trang web: $description",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            loadUrl(Constants.URL+newsUrl)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.webView.onPause()
    }
}