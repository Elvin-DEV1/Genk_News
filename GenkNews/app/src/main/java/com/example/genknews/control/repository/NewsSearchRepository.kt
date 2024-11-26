package com.example.genknews.control.repository

import com.example.genknews.control.api.NewsAPI
import com.example.genknews.control.api.RetrofitInstance

class NewsSearchRepository {

    suspend fun search(keywords: String, pageIndex: String = "1", pageSize: String = "20") =
        RetrofitInstance.api.search(
            NewsAPI.SearchRequest(
                keywords = keywords,
                pageIndex = pageIndex,
                pageSize = pageSize
            )
        )
}