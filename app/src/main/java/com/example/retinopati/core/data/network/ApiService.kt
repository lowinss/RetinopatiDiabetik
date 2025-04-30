package com.example.retinopati.core.data.network

import com.example.retinopati.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.request.forms.formData
import io.ktor.client.request.forms.submitFormWithBinaryData
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.Headers
import io.ktor.http.HttpHeaders
import java.io.File

interface ApiService {
    suspend fun predict(file: File): HttpResponse
}

class KtorApiService(private val client: HttpClient) : ApiService {
    private val baseUrl: String = BuildConfig.BASE_URL
    override suspend fun predict(file: File): HttpResponse {
        val contentType = when (file.extension.lowercase()) {
            "jpg", "jpeg" -> ContentType.Image.JPEG
            "png" -> ContentType.Image.PNG
            else -> ContentType.Application.OctetStream
        }
        return client.submitFormWithBinaryData(
            url = "${baseUrl}/predict",
            formData = formData {
                append("file", file.readBytes(), Headers.build {
                    append(HttpHeaders.ContentType, contentType.toString())
                    append(HttpHeaders.ContentDisposition, "filename=\"${file.name}\"")
                })
            }
        )
    }
}