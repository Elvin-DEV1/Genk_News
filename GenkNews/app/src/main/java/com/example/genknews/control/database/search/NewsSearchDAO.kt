package com.example.genknews.control.database.search

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genknews.control.entity.NewsSearchDB

interface NewsSearchDAO {
    @Query("SELECT * FROM news")
    suspend fun getAllNews(): LiveData<List<NewsSearchDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsSearchDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<NewsSearchDB>)

    @Query("SELECT * FROM news WHERE news_id = :id")
    suspend fun getNewsById(id: String): NewsSearchDB?

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()
}