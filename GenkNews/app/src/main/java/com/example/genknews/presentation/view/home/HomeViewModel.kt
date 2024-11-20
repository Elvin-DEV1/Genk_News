package com.example.genknews.presentation.view.home

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.genknews.common.entity.NewsHomeResponse
import com.example.genknews.common.entity.MenuResponse
import com.example.genknews.common.entity.NewsLatestResponse
import com.example.genknews.common.entity.SearchResponse
import com.example.genknews.common.entity.CategoryNewsResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.CategoryRepository
import com.example.genknews.control.repository.NewsCategoryRepository
import com.example.genknews.control.repository.NewsHomeRepository
import com.example.genknews.control.repository.NewsLatestRepository
import com.example.genknews.control.repository.NewsSearchRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class HomeViewModel(app: Application,
                    val newsHomeRepository: NewsHomeRepository,
                    val newsCategoryRepository: NewsCategoryRepository,
                    val newsLatestRepository: NewsLatestRepository,
                    val categoryRepository: CategoryRepository,
                    val newsSearchRepository: NewsSearchRepository
) : AndroidViewModel(app) {
    /* **********************************************************************
     Home
     ********************************************************************** */
    val home: MutableLiveData<Resource<NewsHomeResponse>> = MutableLiveData()
    var newsHomeResponse: NewsHomeResponse? = null

    private fun handleHomeResponse(response: Response<NewsHomeResponse>): Resource<NewsHomeResponse>{
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                Log.d("HomeViewModel", "Response body: $resultResponse")

                if (newsHomeResponse?.homeNewsPosition?.data  == null){
                    newsHomeResponse = resultResponse
                }else{
                    val oldNews = newsHomeResponse?.homeNewsPosition?.data
                    val newNews = resultResponse.homeNewsPosition.data
                    oldNews?.addAll(newNews)
                }
                return  Resource.Success(newsHomeResponse ?: resultResponse)
            }
        }
        Log.e("HomeViewModel", "Response not successful: ${response.errorBody()?.string()}")
        return Resource.Error(response.body(), response.message())
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun internetConnection(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val capabilities = connectivityManager.getNetworkCapabilities(network)

            return capabilities != null && (
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                    )
        } else {
            @Suppress("DEPRECATION")
            val networkInfo = connectivityManager.activeNetworkInfo
            @Suppress("DEPRECATION")
            return networkInfo != null && networkInfo.isConnected
        }
    }

    private suspend fun homeInternet() {
        home.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                try {
                    val response = newsHomeRepository.getHome()
                    home.postValue(handleHomeResponse(response))
                } catch (e: Exception) {
                    Log.e("HomeViewModel", "Error fetching data", e)
                    home.postValue(Resource.Error(newsHomeResponse, e.message ?: "An error occurred"))
                }
            } else {
                home.postValue(Resource.Error(newsHomeResponse, "No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> home.postValue(Resource.Error(newsHomeResponse, "Network error: Unable to connect"))
                else -> {
                    Log.e("HomeViewModel", "Unknown error", t)
                    home.postValue(Resource.Error(newsHomeResponse, "Unknown error occurred: ${t.message}"))
                }
            }
        }
    }

    fun getHome() = viewModelScope.launch {
        Log.d("HomeViewModel", "Starting getHome()")
        try {
            homeInternet()
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error in getHome()", e)
            home.postValue(Resource.Error(newsHomeResponse, "Failed to fetch home data: ${e.message}"))
        }
    }

    /* **********************************************************************
 Latest
 ********************************************************************** */
    val latest: MutableLiveData<Resource<NewsLatestResponse>> = MutableLiveData()
    var latestResponse: NewsLatestResponse? = null

    private fun handleLatestResponse(response: Response<NewsLatestResponse>): Resource<NewsLatestResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                Log.d("HomeViewModel", "Latest Response body: $resultResponse")

                if (latestResponse?.news == null){
                    latestResponse = resultResponse
                }else{
                    val oldNews = latestResponse?.news
                    val newNews = resultResponse.news
                    oldNews?.addAll(newNews)
                }
                return Resource.Success(latestResponse ?: resultResponse)
            }
        }
        Log.e("HomeViewModel", "Latest Response not successful: ${response.errorBody()?.string()}")
        return Resource.Error(response.body(), response.message())
    }

    private suspend fun latestInternet() {
        latest.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                try {
                    val response = newsLatestRepository.getLatestNews()
                    latest.postValue(handleLatestResponse(response))
                } catch (e: Exception) {
                    Log.e("HomeViewModel", "Error fetching latest data", e)
                    latest.postValue(Resource.Error(latestResponse, e.message ?: "An error occurred"))
                }
            } else {
                latest.postValue(Resource.Error(latestResponse, "No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> latest.postValue(Resource.Error(latestResponse, "Network error: Unable to connect"))
                else -> {
                    Log.e("HomeViewModel", "Unknown error in latest", t)
                    latest.postValue(Resource.Error(latestResponse, "Unknown error occurred: ${t.message}"))
                }
            }
        }
    }

    fun getLatest() = viewModelScope.launch {
        Log.d("HomeViewModel", "Starting getLatest()")
        try {
            latestInternet()
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error in getLatest()", e)
            latest.postValue(Resource.Error(latestResponse, "Failed to fetch latest data: ${e.message}"))
        }
    }


    /* **********************************************************************
     Category
     ********************************************************************** */
    val category: MutableLiveData<Resource<CategoryNewsResponse>> = MutableLiveData()
    var categoryPage = 1
    var categoryResponse: CategoryNewsResponse? = null

    private fun handleZoneResponse(response: Response<CategoryNewsResponse>): Resource<CategoryNewsResponse> {
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
                is java.io.IOException -> category.postValue(Resource.Error(categoryResponse, "Unable to connect"))
                else -> category.postValue(Resource.Error(categoryResponse, "No signal"))
            }
        }
    }

    fun getNewsCategory(zoneId: String) = viewModelScope.launch {
        zoneInternet(zoneId)
    }

    /* **********************************************************************
     Menu
     ********************************************************************** */
    val menu: MutableLiveData<Resource<MenuResponse>> = MutableLiveData()
    var menuPage = 1
    var menuResponse: MenuResponse? = null

    private fun handleCategoryResponse(response: Response<MenuResponse>): Resource<MenuResponse> {
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

    private suspend fun categoryInternet() {
        menu.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                val response = categoryRepository.getMenu()
                menu.postValue(handleCategoryResponse(response))
            } else {
                menu.postValue(Resource.Error(menuResponse, "No internet"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> menu.postValue(Resource.Error(menuResponse, "Unable to connect"))
                else -> menu.postValue(Resource.Error(menuResponse, "No signal"))
            }
        }
    }

    fun getCategory() = viewModelScope.launch {
        categoryInternet()
    }

    /* **********************************************************************
     Search
     ********************************************************************** */
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
                is java.io.IOException -> search.postValue(Resource.Error(searchResponse, "Unable to connect"))
                else -> search.postValue(Resource.Error(searchResponse, "No signal"))
            }
        }
    }

    fun getSearch(searchQuery: String) = viewModelScope.launch {
        searchInternet(searchQuery)
    }
}