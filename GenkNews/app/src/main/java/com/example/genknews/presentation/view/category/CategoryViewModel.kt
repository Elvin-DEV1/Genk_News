package com.example.genknews.presentation.view.category

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.genknews.common.entity.ZoneNewsResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsCategoryRepository
import retrofit2.Response

class CategoryViewModel(app: Application, val newsCategoryRepository: NewsCategoryRepository) : AndroidViewModel(app) {
    val category: MutableLiveData<Resource<ZoneNewsResponse>> = MutableLiveData()
    var categoryPage = 1
    var categoryResponse: ZoneNewsResponse? = null

    private fun handleZoneNewsResponse(response: Response<ZoneNewsResponse>): Resource<ZoneNewsResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                categoryPage++
                if (categoryResponse?.news  == null){
                    categoryResponse = resultResponse
                }else{
                    val oldNews = categoryResponse?.news
                    val newNews = resultResponse.news
                    oldNews?.addAll(newNews)
                }
                return  Resource.Success(categoryResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.body(), response.message())
    }
}