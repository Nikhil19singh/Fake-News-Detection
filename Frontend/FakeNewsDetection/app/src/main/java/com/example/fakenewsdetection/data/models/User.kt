package com.example.fakenewsdetection.data.models

data class User(
    val email: String,
    val password: String,
    val username: String? = null
)