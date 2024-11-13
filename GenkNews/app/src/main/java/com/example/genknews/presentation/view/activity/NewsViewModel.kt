package com.example.genknews.presentation.view.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.genknews.common.entity.HomeResponse
import com.example.genknews.common.entity.NewsResponse
import com.example.genknews.common.entity.SearchResponse
import com.example.genknews.common.entity.ZoneResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.NewsLatestRepository

class NewsViewModel(app: Application, val newsLatestRepository: NewsLatestRepository): AndroidViewModel(app) {
    val home: MutableLiveData<Resource<HomeResponse>> = MutableLiveData()
    var homePage = 1
    var homeResponse: HomeResponse? = null

    val latest: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var latestPage = 1
    var latestResponse: NewsResponse? = null

    val zone: MutableLiveData<Resource<ZoneResponse>> = MutableLiveData()
    var zonePage = 1
    var zoneResponse: ZoneResponse? = null

    val searchNews: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: SearchResponse? = null
    var newSearchQuery: String? = null
    var oldSearchQuery: String? = null
}