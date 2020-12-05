package com.hemanth.newsapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hemanth.newsapp.api.ApiServices
import com.hemanth.newsapp.views.viewmodel.NewsViewModel
import java.lang.IllegalArgumentException

class ViewModelProviderFactory(private val apiServices: ApiServices): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(apiServices) as T
        }
        throw IllegalArgumentException("")
    }
}