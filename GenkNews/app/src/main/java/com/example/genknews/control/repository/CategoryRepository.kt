package com.example.genknews.control.repository

import com.example.genknews.control.api.RetrofitInstance

class CategoryRepository() {

    suspend fun getMenu() = RetrofitInstance.api.getMenu()

}