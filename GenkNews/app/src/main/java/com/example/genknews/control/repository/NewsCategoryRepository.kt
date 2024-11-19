package com.example.genknews.control.repository

import com.example.genknews.control.api.RetrofitInstance

class NewsCategoryRepository {
    suspend fun getZoneNews(zoneId: String)
            = RetrofitInstance.api.getZoneNews(zoneId = zoneId)
}