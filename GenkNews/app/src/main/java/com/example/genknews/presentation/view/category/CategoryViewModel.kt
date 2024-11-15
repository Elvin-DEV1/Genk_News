package com.example.genknews.presentation.view.category

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.genknews.common.entity.ZoneNewsResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsCategoryRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class CategoryViewModel(app: Application, val newsCategoryRepository: NewsCategoryRepository) : AndroidViewModel(app) {
    val category: MutableLiveData<Resource<ZoneNewsResponse>> = MutableLiveData()
    var categoryPage = 1
    var categoryResponse: ZoneNewsResponse? = null

    private fun handleZoneResponse(response: Response<ZoneNewsResponse>): Resource<ZoneNewsResponse> {
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

    private suspend fun zoneInternet(zoneId: String) {
        category.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                val response = newsCategoryRepository.getZoneNews(zoneId)
                category.postValue(handleZoneResponse(response))
            } else {
                category.postValue(Resource.Error(categoryResponse, "No internet"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> category.postValue(Resource.Error(categoryResponse, "Unable to connect"))
                else -> category.postValue(Resource.Error(categoryResponse, "No signal"))
            }
        }
    }

    fun getZone(zoneId: String) = viewModelScope.launch {
        zoneInternet(zoneId)
    }
}