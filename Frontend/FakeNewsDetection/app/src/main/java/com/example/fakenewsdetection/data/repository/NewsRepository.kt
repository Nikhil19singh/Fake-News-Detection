package com.example.fakenewsdetection.data.repository

import com.example.fakenewsdetection.data.api.RealNewsRetrofitInstance
import com.example.fakenewsdetection.data.api.FakeNewsRetrofitInstance
import com.example.fakenewsdetection.data.models.NewsRequest
import com.example.fakenewsdetection.data.models.NewsResponse
import com.example.fakenewsdetection.data.models.PredictionResponse
import retrofit2.Response

class NewsRepository {

    // ✅ =========================
    // ✅ REAL NEWS (INDIA ONLY - GNEWS)
    // ✅ =========================
    suspend fun getTopHeadlines(): Response<NewsResponse> {
        return RealNewsRetrofitInstance.api.getTopHeadlines()
    }

    // ✅ =========================
    // ✅ FAKE NEWS DETECTION (ML API)
    // ✅ =========================
    suspend fun predictNews(text: String): Response<PredictionResponse> {
        return FakeNewsRetrofitInstance.api.predictNews(
            NewsRequest(text)
        )
    }
}
