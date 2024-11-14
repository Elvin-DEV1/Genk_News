package com.example.genknews.presentation.view.latest

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.genknews.common.entity.NewsResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsLatestRepository
import retrofit2.Response

class LatestViewModel(app: Application, val newsLatestRepository: NewsLatestRepository) : AndroidViewModel(app) {
    val latest: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var latestPage = 1
    var latestResponse: NewsResponse? = null

    private fun handleLatestResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                latestPage++
                if (latestResponse?.news  == null){
                    latestResponse = resultResponse
                }else{
                    val oldNews = latestResponse?.news
                    val newNews = resultResponse.news
                    oldNews?.addAll(newNews)
                }
                return  Resource.Success(latestResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.body(), response.message())
    }
}