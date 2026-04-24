package com.example.fakenewsdetection.data.models

data class PredictionResponse(
    val input_text: String,
    val prediction: String,
    val confidence: Double
)
