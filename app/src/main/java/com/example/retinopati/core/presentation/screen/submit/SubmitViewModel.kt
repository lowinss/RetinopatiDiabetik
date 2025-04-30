package com.example.retinopati.core.presentation.screen.submit

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.retinopati.core.data.model.PredictionResponse
import com.example.retinopati.core.data.network.ApiService
import io.ktor.client.call.body
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

class SubmitViewModel(private val apiService: ApiService) : ViewModel() {
    private val _prediction = MutableStateFlow<PredictionResponse?>(null)
    val prediction : StateFlow<PredictionResponse?> = _prediction

    private val _isLoading = mutableStateOf(false)
    val isLoading : State<Boolean> = _isLoading

    suspend fun predict(file: File): PredictionResponse {
        _isLoading.value = true
        return try {
            val response = apiService.predict(file)
            response.body()
        } catch (e: Exception) {
            Log.e("Prediction Error", "Failed: ${e.message}")
            throw e
        } finally {
            _isLoading.value = false
        }
    }
}