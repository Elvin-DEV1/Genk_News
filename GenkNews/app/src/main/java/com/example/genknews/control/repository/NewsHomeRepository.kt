package com.example.genknews.control.repository

import com.example.genknews.control.api.RetrofitInstance
import com.example.genknews.control.database.home.NewsHomeDatabase
import com.example.genknews.control.entity.NewsHomeDB

class NewsHomeRepository(val db: NewsHomeDatabase) {

    suspend fun getHome() =
        RetrofitInstance.api.getHome()

    suspend fun getAllNewsHome() = db.getNewsDao().getAllNews()

    suspend fun insertNewsHome(news: NewsHomeDB) = db.getNewsDao().insertNews(news)

    suspend fun insertAllNewsHome(news: List<NewsHomeDB>) = db.getNewsDao().insertAllNews(news)

    suspend fun getNewsByIdHome(id: String): NewsHomeDB? = db.getNewsDao().getNewsById(id)

    suspend fun deleteAllNewsHome() = db.getNewsDao().deleteAllNews()
}