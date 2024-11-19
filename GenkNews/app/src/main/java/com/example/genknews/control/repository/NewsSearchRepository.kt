package com.example.genknews.control.repository

import com.example.genknews.control.api.NewsAPI
import com.example.genknews.control.api.RetrofitInstance

class NewsSearchRepository {

    suspend fun search(keywords: String)
            = RetrofitInstance.api.search(
        NewsAPI.SearchRequest(keywords = keywords)
    )
}