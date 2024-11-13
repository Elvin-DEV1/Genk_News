package com.example.genknews.control.database.home

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genknews.control.entity.NewsHomeDB

interface NewsHomeDAO {
    @Query("SELECT * FROM news")
    suspend fun getAllNews(): LiveData<List<NewsHomeDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsHomeDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<NewsHomeDB>)

    @Query("SELECT * FROM news WHERE news_id = :id")
    suspend fun getNewsById(id: String): NewsHomeDB?

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()
}