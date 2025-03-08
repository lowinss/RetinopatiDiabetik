package com.example.retinopati.core.presentation.navigation

import android.net.Uri
import kotlinx.serialization.Serializable

sealed interface Routes {
    @Serializable
    data object InitialScreen: Routes

    @Serializable
    data object HomeScreen: Routes

    @Serializable
    data object SubmitScreen: Routes

    @Serializable
    data class ResultScreen(val uri: String): Routes

    @Serializable
    data object InformationScreen: Routes
}