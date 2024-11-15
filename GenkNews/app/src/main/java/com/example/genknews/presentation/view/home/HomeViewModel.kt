package com.example.genknews.presentation.view.home

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.genknews.common.entity.HomeResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsHomeRepository
import kotlinx.coroutines.launch
import okio.IOException
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

    private suspend fun homeInternet() {
        home.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                val response = newsHomeRepository.getHome()
                home.postValue(handleHomeResponse(response))
            } else {
                home.postValue(Resource.Error(homeResponse, "No internet"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> home.postValue(Resource.Error(homeResponse, "Unable to connect"))
                else -> home.postValue(Resource.Error(homeResponse, "No signal"))
            }
        }
    }

    fun getHome() = viewModelScope.launch {
        homeInternet()
    }
}