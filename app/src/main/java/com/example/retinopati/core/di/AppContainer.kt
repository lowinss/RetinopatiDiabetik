package com.example.retinopati.core.di

import com.example.retinopati.core.data.network.ApiService
import com.example.retinopati.core.data.network.KtorApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

object AppContainer {
    private val httpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }
    val apiService: ApiService = KtorApiService(httpClient)
}