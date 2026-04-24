package com.example.fakenewsdetection.data.api

import com.example.fakenewsdetection.data.models.NewsResponse
import com.example.fakenewsdetection.data.models.NewsRequest
import com.example.fakenewsdetection.data.models.PredictionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NewsApiService {

    // ✅ =========================
    // ✅ GNEWS REAL NEWS API
    // ✅ =========================
    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "in",
        @Query("lang") lang: String = "en",
        @Query("token") apiKey: String = "8356d8a50b7f9fd593d8b99bc6e9400c"
    ): Response<NewsResponse>

    // ✅ =========================
    // ✅ FAKE NEWS DETECTION (ML API)
    // ✅ =========================
    @POST("predict")
    suspend fun predictNews(
        @Body request: NewsRequest
    ): Response<PredictionResponse>
}
