package com.baltajmn.gigi.core.network.service

import com.baltajmn.gigi.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class GigiService(
    private val httpClient: HttpClient
) {

    suspend fun getNearbyLocations(latLong: String): String {
        return try {
            val response: HttpResponse = httpClient.get("/api/v1/location/nearby_search") {
                parameter("key", BuildConfig.API_KEY)
                parameter("latLong", "42.3455,-71.10767")
            }.body()
            response.bodyAsText()
        } catch (e: Exception) {
            ""
        }
    }

}