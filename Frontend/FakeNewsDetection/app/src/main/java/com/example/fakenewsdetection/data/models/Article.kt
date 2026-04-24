package com.example.fakenewsdetection.data.models

data class Article(
    val title: String,
    val description: String?,
    val content: String?,
    val url: String,
    val image: String?,
    val publishedAt: String,
    val source: Source
)
