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

    suspend fun getAllNews() = db.getNewsDao().getAllNews()

    suspend fun insertNews(news: NewsSearchDB) = db.getNewsDao().insertNews(news)

    suspend fun insertAllNews(news: List<NewsSearchDB>) = db.getNewsDao().insertAllNews(news)

    suspend fun getNewsById(id: String): NewsSearchDB? = db.getNewsDao().getNewsById(id)

    suspend fun deleteAllNews() = db.getNewsDao().deleteAllNews()
}