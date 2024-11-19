package com.example.genknews.control.repository

import com.example.genknews.control.api.NewsAPI
import com.example.genknews.control.api.RetrofitInstance

class NewsLatestRepository {

    suspend fun getLatestNews()
    = RetrofitInstance.api.getLatestNews()

    suspend fun search(keywords: String, pageIndex: String, pageSize: String)
    = RetrofitInstance.api.search(
        NewsAPI.SearchRequest(keywords = keywords, pageIndex = pageIndex, pageSize = pageSize)
    )
}