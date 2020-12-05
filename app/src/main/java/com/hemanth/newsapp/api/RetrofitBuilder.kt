package com.hemanth.newsapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private fun getServices(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://newsapi.org/v2/")
            .build()
    }

    val apiServices = getServices().create(ApiServices::class.java)
}