package com.example.retinopati.core.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PredictionResponse(
    val prediction: String,
    val confidence: Double
)
