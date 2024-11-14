package com.example.genknews.control.database.menu

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genknews.control.entity.NewsCategoryDB

interface NewsCategoryDAO {
    @Query("SELECT * FROM news")
    suspend fun getAllNews(): LiveData<List<NewsCategoryDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: NewsCategoryDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<NewsCategoryDB>)

    @Query("SELECT * FROM news WHERE news_id = :id")
    suspend fun getNewsById(id: String): NewsCategoryDB?

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()
}