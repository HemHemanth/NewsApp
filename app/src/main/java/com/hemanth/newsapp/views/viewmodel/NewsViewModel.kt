package com.hemanth.newsapp.views.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hemanth.newsapp.api.ApiServices
import com.hemanth.newsapp.model.Articles
import com.hemanth.newsapp.model.Everything
import com.hemanth.newsapp.utils.Resource
import com.hemanth.newsapp.views.base.BaseViewModel
import kotlinx.coroutines.launch

class NewsViewModel(private val apiServices: ApiServices): BaseViewModel() {
    private val newsList = MutableLiveData<Resource<List<Articles>>>()
    init {
        fetchNews()
    }

    private fun fetchNews() {
        newsList.postValue(Resource.loading(null))
        viewModelScope.launch {
            val everythingList = apiServices.getEverything("bitcoin",
            "2020-11-05",
            "publishedAt",
            "7b9abfa9384e4a26a8986a0b9a7ad8a2").body()?.articles

            if (everythingList != null) {
                newsList.postValue(Resource.success(everythingList))
            } else {
                newsList.postValue(Resource.error("", null))
            }
        }
    }

    fun getNewsList(): LiveData<Resource<List<Articles>>> {
        return newsList
    }
}