package com.example.genknews.control.repository

import com.example.genknews.control.api.NewsAPI
import com.example.genknews.control.api.RetrofitInstance
import com.example.genknews.control.database.latest.NewsLatestDatabase
import com.example.genknews.control.entity.NewsLatestDB

class NewsLatestRepository(val db: NewsLatestDatabase) {

    suspend fun getHome(os: String, version: String)
    = RetrofitInstance.api.getHome(os = os, version = version)

    suspend fun getLatestNews(pageIndex: Int, pageSize: Int)
    = RetrofitInstance.api.getLatestNews(pageIndex = pageIndex, pageSize = pageSize)

    suspend fun getMenu() = RetrofitInstance.api.getMenu()

    suspend fun getConfig() = RetrofitInstance.api.getConfig()

    suspend fun getNotify(deviceId: String, userId: String)
    = RetrofitInstance.api.getNotify(deviceId = deviceId, userId = userId)

    suspend fun getAllZones() = RetrofitInstance.api.getAllZones()

    suspend fun getTag(pageIndex: Int, id: String)
    = RetrofitInstance.api.getTag(pageIndex = pageIndex, id = id)

    suspend fun getVideoZones() = RetrofitInstance.api.getVideoZones()

    suspend fun getZoneNews(pageIndex: Int, zoneId: String)
    = RetrofitInstance.api.getZoneNews(pageIndex = pageIndex, zoneId = zoneId)

    suspend fun getHotVideos() = RetrofitInstance.api.getHotVideos()

    suspend fun getVideoDetail(videoId: String)
    = RetrofitInstance.api.getVideoDetail(videoId = videoId)

    suspend fun search(keywords: String, pageIndex: String, pageSize: String)
    = RetrofitInstance.api.search(
        NewsAPI.SearchRequest(keywords = keywords, pageIndex = pageIndex, pageSize = pageSize)
    )

    suspend fun getAllLatestNews() = db.getNewsDao().getAllNews()

    suspend fun insertLatestNews(news: NewsLatestDB) = db.getNewsDao().insertNews(news)

    suspend fun insertAllLatestNews(news: List<NewsLatestDB>) = db.getNewsDao().insertAllNews(news)

    suspend fun getLatestNewsById(id: String): NewsLatestDB? = db.getNewsDao().getNewsById(id)

    suspend fun deleteAllLatestNews() = db.getNewsDao().deleteAllNews()
}