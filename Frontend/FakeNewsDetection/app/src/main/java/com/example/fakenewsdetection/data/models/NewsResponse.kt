package com.example.fakenewsdetection.data.models

data class NewsResponse(
    val totalArticles: Int,
    val articles: List<Article>
)
