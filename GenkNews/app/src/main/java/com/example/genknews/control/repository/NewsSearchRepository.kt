package com.example.genknews.control.repository

import com.example.genknews.control.api.NewsAPI
import com.example.genknews.control.api.RetrofitInstance
import com.example.genknews.control.database.search.NewsSearchDatabase
import com.example.genknews.control.entity.NewsSearchDB

class NewsSearchRepository(val db: NewsSearchDatabase) {

    suspend fun search(keywords: String, pageIndex: String, pageSize: String)
            = RetrofitInstance.api.search(
        NewsAPI.SearchRequest(keywords = keywords, pageIndex = pageIndex, pageSize = pageSize)
    )

    suspend fun getAllNewsSearch() = db.getNewsDao().getAllNews()

    suspend fun insertNewsSearch(news: NewsSearchDB) = db.getNewsDao().insertNews(news)

    suspend fun insertAllNewsSearch(news: List<NewsSearchDB>) = db.getNewsDao().insertAllNews(news)

    suspend fun getNewsSearchById(id: String): NewsSearchDB? = db.getNewsDao().getNewsById(id)

    suspend fun deleteAllNewsSearch() = db.getNewsDao().deleteAllNews()
}