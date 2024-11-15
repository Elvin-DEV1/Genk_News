package com.example.genknews.presentation.view.latest

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.genknews.common.entity.NewsResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsLatestRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

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

    private fun internetConnection(context: Context): Boolean {
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            return getNetworkCapabilities(activeNetwork)?.run {
                when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }
            } ?: false
        }
    }

    private suspend fun latestInternet() {
        latest.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                val response = newsLatestRepository.getLatestNews()
                latest.postValue(handleLatestResponse(response))
            } else {
                latest.postValue(Resource.Error(latestResponse, "No internet"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> latest.postValue(Resource.Error(latestResponse, "Unable to connect"))
                else -> latest.postValue(Resource.Error(latestResponse, "No signal"))
            }
        }
    }

    fun getLatest() = viewModelScope.launch {
        latestInternet()
    }
}