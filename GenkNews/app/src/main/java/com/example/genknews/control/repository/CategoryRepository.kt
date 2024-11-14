package com.example.genknews.control.repository

import com.example.genknews.control.api.RetrofitInstance
import com.example.genknews.control.database.category.CategoryDatabase
import com.example.genknews.control.entity.CategoryDB

class CategoryRepository(val db: CategoryDatabase) {

    suspend fun getMenu() = RetrofitInstance.api.getMenu()

    suspend fun getAllCategory() = db.getNewsDao().getAllNews()

    suspend fun insertCategory(news: CategoryDB) = db.getNewsDao().insertNews(news)

    suspend fun insertAllCategory(news: List<CategoryDB>) = db.getNewsDao().insertAllNews(news)

    suspend fun getCategoryById(id: String): CategoryDB? = db.getNewsDao().getNewsById(id)

    suspend fun deleteAllCategory() = db.getNewsDao().deleteAllNews()
}