package com.example.genknews.control.repository

import com.example.genknews.control.api.RetrofitInstance
import com.example.genknews.control.database.home.NewsHomeDatabase
import com.example.genknews.control.entity.NewsHomeDB

class NewsHomeRepository(val db: NewsHomeDatabase) {

    suspend fun getHome(os: String, version: String) =
        RetrofitInstance.api.getHome(os = os, version = version)

    suspend fun getAllNews() = db.getNewsDao().getAllNews()

    suspend fun insertNews(news: NewsHomeDB) = db.getNewsDao().insertNews(news)

    suspend fun insertAllNews(news: List<NewsHomeDB>) = db.getNewsDao().insertAllNews(news)

    suspend fun getNewsById(id: String): NewsHomeDB? = db.getNewsDao().getNewsById(id)

    suspend fun deleteAllNews() = db.getNewsDao().deleteAllNews()
}