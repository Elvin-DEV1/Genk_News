package com.example.genknews.presentation.view.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.genknews.common.entity.HomeResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsHomeRepository
import retrofit2.Response

class HomeViewModel(app: Application, val newsHomeRepository: NewsHomeRepository) : AndroidViewModel(app) {
    val home: MutableLiveData<Resource<HomeResponse>> = MutableLiveData()
    var homePage = 1
    var homeResponse: HomeResponse? = null

    private fun handleHomeResponse(response: Response<HomeResponse>): Resource<HomeResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                homePage++
                if (homeResponse?.homeNewsPosition?.data  == null){
                    homeResponse = resultResponse
                }else{
                    val oldNews = homeResponse?.homeNewsPosition?.data
                    val newNews = resultResponse.homeNewsPosition.data
                    oldNews?.addAll(newNews)
                }
                return  Resource.Success(homeResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.body(), response.message())
    }
}