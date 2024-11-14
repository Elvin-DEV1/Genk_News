package com.example.genknews.control.repository

import com.example.genknews.control.api.RetrofitInstance
import com.example.genknews.control.database.menu.NewsCategoryDatabase
import com.example.genknews.control.entity.NewsCategoryDB

class NewsCategoryRepository(val db: NewsCategoryDatabase) {

    suspend fun getZoneNews(pageIndex: Int, zoneId: String)
            = RetrofitInstance.api.getZoneNews(pageIndex = pageIndex, zoneId = zoneId)

    suspend fun getAllNewsZone() = db.getNewsDao().getAllNews()

    suspend fun insertNewsZone(news: NewsCategoryDB) = db.getNewsDao().insertNews(news)

    suspend fun insertAllNewsZone(news: List<NewsCategoryDB>) = db.getNewsDao().insertAllNews(news)

    suspend fun getNewsZoneById(id: String): NewsCategoryDB? = db.getNewsDao().getNewsById(id)

    suspend fun deleteAllNewsZone() = db.getNewsDao().deleteAllNews()
}