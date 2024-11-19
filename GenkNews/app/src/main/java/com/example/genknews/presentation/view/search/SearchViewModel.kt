package com.example.genknews.presentation.view.search

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.genknews.common.entity.SearchResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsSearchRepository
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class SearchViewModel(app: Application, val newsSearchRepository: NewsSearchRepository): AndroidViewModel(app) {
    val search: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    var searchPage = 1
    var searchResponse: SearchResponse? = null

    private fun handleSearchResponse(response: Response<SearchResponse>): Resource<SearchResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                searchPage++
                if (searchResponse?.news  == null){
                    searchResponse = resultResponse
                }else{
                    val oldNews = searchResponse?.news
                    val newNews = resultResponse.news
                    oldNews?.addAll(newNews)
                }
                return  Resource.Success(searchResponse ?: resultResponse)
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

    private suspend fun searchInternet(searchQuery: String) {
        search.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                val response = newsSearchRepository.search(searchQuery)
                search.postValue(handleSearchResponse(response))
            } else {
                search.postValue(Resource.Error(searchResponse, "No internet"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> search.postValue(Resource.Error(searchResponse, "Unable to connect"))
                else -> search.postValue(Resource.Error(searchResponse, "No signal"))
            }
        }
    }

    fun getSearch(searchQuery: String) = viewModelScope.launch {
        searchInternet(searchQuery)
    }
    
}