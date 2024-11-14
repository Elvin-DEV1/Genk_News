package com.example.genknews.control.database.category

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.genknews.control.entity.CategoryDB

interface CategoryDAO {
    @Query("SELECT * FROM news")
    suspend fun getAllNews(): LiveData<List<CategoryDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: CategoryDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllNews(news: List<CategoryDB>)

    @Query("SELECT * FROM news WHERE news_id = :id")
    suspend fun getNewsById(id: String): CategoryDB?

    @Query("DELETE FROM news")
    suspend fun deleteAllNews()
}