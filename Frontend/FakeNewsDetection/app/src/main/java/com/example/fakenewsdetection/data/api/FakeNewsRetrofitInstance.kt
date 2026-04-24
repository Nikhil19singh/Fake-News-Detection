package com.example.fakenewsdetection.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object FakeNewsRetrofitInstance {

    private const val BASE_URL = "http://10.199.15.214:8000/"   // Emulator

    val api: NewsApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApiService::class.java)
    }
}
