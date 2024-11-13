package com.example.genknews.control.database.latest

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genknews.control.entity.NewsLatestDB

@Dao
interface NewsLatestDAO {
    @Query("SELECT * FROM news")
    suspend fun getAllNews(): LiveData<List<NewsLatestDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsLatestDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<NewsLatestDB>)

    @Query("SELECT * FROM news WHERE news_id = :id")
    suspend fun getNewsById(id: String): NewsLatestDB?

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()
}