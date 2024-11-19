package com.example.genknews.control.repository

import com.example.genknews.control.api.RetrofitInstance

class NewsHomeRepository {
    suspend fun getHome() =
        RetrofitInstance.api.getHome()
}