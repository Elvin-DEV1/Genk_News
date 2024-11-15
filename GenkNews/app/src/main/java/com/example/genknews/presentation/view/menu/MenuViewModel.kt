package com.example.genknews.presentation.view.menu

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.genknews.common.entity.MenuResponse
import com.example.genknews.common.utils.Resource
import com.example.genknews.control.repository.CategoryRepository
import kotlinx.coroutines.launch
import okio.IOException
import retrofit2.Response

class MenuViewModel(app: Application, val categoryRepository: CategoryRepository) : AndroidViewModel(app) {
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
}