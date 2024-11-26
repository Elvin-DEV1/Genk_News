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
import com.example.genknews.common.entity.CategoryNewsResponse
import com.example.genknews.common.entity.MenuResponse
import com.example.genknews.common.entity.NewsHomeResponse
import com.example.genknews.common.entity.NewsLatestResponse
import com.example.genknews.common.entity.SearchResponse
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
    var categoryResponse: CategoryNewsResponse? = null

    private fun handleZoneResponse(response: Response<CategoryNewsResponse>): Resource<CategoryNewsResponse> {
        if (response.isSuccessful){
            response.body()?.let { resultResponse ->
                Log.d("HomeViewModel", "Latest Response body: $resultResponse")
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
        Log.e("HomeViewModel", "Category Response not successful: ${response.errorBody()?.string()}")
        return Resource.Error(response.body(), response.message())
    }

    private suspend fun zoneInternet(zoneId: String) {
        category.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                try {
                    val response = newsCategoryRepository.getZoneNews(zoneId)
                    category.postValue(handleZoneResponse(response))
                } catch (e: Exception) {
                    Log.e("HomeViewModel", "Error fetching category data", e)
                    category.postValue(Resource.Error(categoryResponse, e.message ?: "An error occurred"))
                }
            } else {
                category.postValue(Resource.Error(categoryResponse, "No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> category.postValue(Resource.Error(categoryResponse, "Network error: Unable to connect"))
                else -> {
                    Log.e("HomeViewModel", "Unknown error in category", t)
                    category.postValue(Resource.Error(categoryResponse, "Unknown error occurred: ${t.message}"))
                }
            }
        }
    }

    fun getNewsCategory(zoneId: String) = viewModelScope.launch {
        Log.d("HomeViewModel", "Starting getNewsCategory()")
        try {
            zoneInternet(zoneId)
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error in getNewsCategory()", e)
            category.postValue(Resource.Error(categoryResponse, "Failed to fetch category data: ${e.message}"))
        }
    }

    /* **********************************************************************
     Menu
     ********************************************************************** */
    val menu: MutableLiveData<Resource<MenuResponse>> = MutableLiveData()
    var menuResponse: MenuResponse? = null

    private fun handleCategoryResponse(response: Response<MenuResponse>): Resource<MenuResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
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
                try {
                    val response = categoryRepository.getMenu()
                    menu.postValue(handleCategoryResponse(response))
                } catch (e: Exception) {
                    Log.e("HomeViewModel", "Error fetching menu data", e)
                    menu.postValue(Resource.Error(menuResponse, e.message ?: "An error occurred"))
                }
            } else {
                menu.postValue(Resource.Error(menuResponse, "No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> menu.postValue(Resource.Error(menuResponse, "Network error: Unable to connect"))
                else -> {
                    Log.e("HomeViewModel", "Unknown error in menu", t)
                    menu.postValue(Resource.Error(menuResponse, "Unknown error occurred: ${t.message}"))
                }
            }
        }
    }

    fun getCategory() = viewModelScope.launch {
        Log.d("HomeViewModel", "Starting getCategory()")
        try {
            categoryInternet()
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error in getCategory()", e)
            menu.postValue(Resource.Error(menuResponse, "Failed to fetch menu data: ${e.message}"))
        }
    }

    /* **********************************************************************
     Search
     ********************************************************************** */
    val search: MutableLiveData<Resource<SearchResponse>> = MutableLiveData()
    var searchResponse: SearchResponse? = null

    private fun handleSearchResponse(response: Response<SearchResponse>): Resource<SearchResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                if (searchResponse?.news == null) {
                    searchResponse = resultResponse
                } else {
                    val oldNews = searchResponse?.news
                    val newNews = resultResponse.news
                    oldNews?.addAll(newNews)
                }
                return Resource.Success(searchResponse ?: resultResponse)
            }
        }
        return Resource.Error(response.body(), response.message())
    }


    private suspend fun searchInternet(
        searchQuery: String,
        pageIndex: String = "1",
        pageSize: String = "20"
    ) {
        search.postValue(Resource.Loading())
        try {
            if (internetConnection(this.getApplication())) {
                try {
                    val response = newsSearchRepository.search(
                        keywords = searchQuery,
                        pageIndex = pageIndex,
                        pageSize = pageSize
                    )
                    search.postValue(handleSearchResponse(response))
                } catch (e: Exception) {
                    Log.e("HomeViewModel", "Error fetching search data", e)
                    search.postValue(
                        Resource.Error(searchResponse, e.message ?: "An error occurred")
                    )
                }
            } else {
                search.postValue(Resource.Error(searchResponse, "No internet connection"))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> search.postValue(Resource.Error(searchResponse, "Network error: Unable to connect"))
                else -> {
                    Log.e("HomeViewModel", "Unknown error in search", t)
                    search.postValue(Resource.Error(searchResponse, "Unknown error occurred: ${t.message}"))
                }
            }
        }
    }


    fun getSearch(
        searchQuery: String,
        pageIndex: String = "1",
        pageSize: String = "20"
    ) = viewModelScope.launch {
        Log.d("HomeViewModel", "Starting getSearch()")
        try {
            searchInternet(searchQuery, pageIndex, pageSize)
        } catch (e: Exception) {
            Log.e("HomeViewModel", "Error in getSearch()", e)
            search.postValue(
                Resource.Error(searchResponse, "Failed to fetch search data: ${e.message}")
            )
        }
    }

}