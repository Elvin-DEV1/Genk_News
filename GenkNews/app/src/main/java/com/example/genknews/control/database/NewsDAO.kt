package com.example.genknews.control.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genknews.control.entity.NewsDB

@Dao
interface NewsDAO {
    @Query("SELECT * FROM news")
    fun getAllNews(): LiveData<List<NewsDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<NewsDB>)

    @Query("SELECT * FROM news WHERE news_id = :id")
    suspend fun getNewsById(id: String): NewsDB?

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()
}