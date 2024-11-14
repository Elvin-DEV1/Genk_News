package com.example.genknews.presentation.view.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.genknews.common.entity.MenuResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsCategoryRepository
import retrofit2.Response

class MenuViewModel(app: Application, val categoryRepository: NewsCategoryRepository) : AndroidViewModel(app) {
    val menu: MutableLiveData<Resource<MenuResponse>> = MutableLiveData()
    var menuPage = 1
    var menuResponse: MenuResponse? = null

    private fun handleZoneNewsResponse(response: Response<MenuResponse>): Resource<MenuResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                menuPage++
                if (menuResponse?.categoriesBox == null) {
                    menuResponse = resultResponse
                } else {
                    val oldNews = menuResponse?.categoriesBox
                    val newNews = resultResponse.categoriesBox
                    oldNews?.addAll(newNews)
                }
                return Resource.Success(menuResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.body(), response.message())
    }
}