package com.example.genknews.presentation.view.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.genknews.common.entity.HomeResponse
import com.example.genknews.common.entity.SearchResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsSearchRepository
import retrofit2.Response

class SearchViewModel(app: Application, val newsSearchRepository: NewsSearchRepository): AndroidViewModel(app) {
    val search: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    var searchPage = 1
    var searchResponse: SearchResponse? = null
    var oldQuery: String? = null
    var newQuery: String? = null

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
}