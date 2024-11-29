package com.example.genknews.presentation.fragments

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.genknews.R
import com.example.genknews.common.utils.Constants
import com.example.genknews.databinding.FragmentArticleBinding
import com.example.genknews.presentation.activity.MainActivity
import com.example.genknews.presentation.view.home.HomeViewModel

class ArticleFragment : Fragment(R.layout.fragment_article) {
    private var _binding: FragmentArticleBinding? = null
    private val binding get() = _binding!!
    lateinit var newsViewModel: HomeViewModel

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("current_url", binding.webView.url)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentArticleBinding.bind(view)

        newsViewModel = (activity as MainActivity).homeViewModel

        val savedUrl = savedInstanceState?.getString("current_url")
        val newsUrl = savedUrl ?: arguments?.getString("url") ?: arguments?.getString("urlRelation")
        Log.d("ArticleFragment", "Received URL: $newsUrl")

        if (newsUrl.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "URL không hợp lệ", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
            return
        }

        val fullUrl = if (arguments?.containsKey("urlRelation") == true) {
            newsUrl
        } else {
            Constants.URL + newsUrl
        }.let { url ->
            if (url.startsWith("http://genk.vn")) {
                url.replace("http://", "https://")
            } else {
                url
            }
        }

        binding.close.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.imgCopy.setOnClickListener {
            copyToClipboard(fullUrl)
            Toast.makeText(requireContext(), "Đã sao chép liên kết", Toast.LENGTH_SHORT).show()
        }

        binding.send.setOnClickListener {
            shareArticle(fullUrl)
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

            loadUrl(fullUrl)
        }
    }

    private fun copyToClipboard(text: String) {
        val clipboard =
            requireContext().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("article_url", text)
        clipboard.setPrimaryClip(clip)
    }

    private fun shareArticle(url: String) {
        try {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
            }

            val chooserIntent = Intent.createChooser(
                shareIntent,
                "Chia sẻ qua"
            )

            startActivity(chooserIntent)
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                "Không thể chia sẻ bài viết: ${e.message}",
                Toast.LENGTH_SHORT
            ).show()
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