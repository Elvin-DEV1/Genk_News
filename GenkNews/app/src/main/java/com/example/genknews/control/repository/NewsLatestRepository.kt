package com.example.genknews.control.repository

import com.example.genknews.control.api.NewsAPI
import com.example.genknews.control.api.RetrofitInstance

class NewsLatestRepository {

    suspend fun getLatestNews()
    = RetrofitInstance.api.getLatestNews()
}