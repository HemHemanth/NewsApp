package com.hemanth.newsapp.api

import com.hemanth.newsapp.model.Everything
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    @GET("everything")
    suspend fun getEverything(@Query("q") q: String = "bitcoin",
                              @Query("from") from: String = "2020-11-05",
                              @Query("sortBy") sortBy: String = "publishedAt",
                              @Query("apiKey") apiKey: String = "7b9abfa9384e4a26a8986a0b9a7ad8a2")
    : Response<Everything>
}