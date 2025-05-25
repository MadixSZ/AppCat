package com.example.catapp.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CatApiService {
    @GET("/cat?json=true")
    suspend fun getRandomCat(): CatResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://cataas.com/"

    val instance: CatApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatApiService::class.java)
    }
}