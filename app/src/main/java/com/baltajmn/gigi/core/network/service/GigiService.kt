package com.baltajmn.gigi.core.network.service

import com.baltajmn.gigi.BuildConfig
import com.baltajmn.gigi.core.network.model.DetailsApi
import com.baltajmn.gigi.core.network.model.LocationApi
import com.baltajmn.gigi.core.network.model.ResponseLocationApi
import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText

class GigiService(
    private val httpClient: HttpClient
) {

    suspend fun getLocations(name: String, latLong: String): List<LocationApi> {
        return try {
            val response: HttpResponse = httpClient.get("/api/v1/location/search") {
                parameter("key", BuildConfig.API_KEY)
                parameter("category", "restaurants")
                parameter("latLong", latLong)
                parameter("searchQuery", name)
            }.body()

            Gson().fromJson(
                response.bodyAsText(),
                ResponseLocationApi::class.java
            ).data
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getNearbyLocations(latLong: String): List<LocationApi> {
        return try {
            val response: HttpResponse = httpClient.get("/api/v1/location/nearby_search") {
                parameter("key", BuildConfig.API_KEY)
                parameter("category", "restaurants")
                parameter("latLong", latLong)
            }.body()

            Gson().fromJson(
                response.bodyAsText(),
                ResponseLocationApi::class.java
            ).data
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getDetails(id: String): DetailsApi {
        return try {
            val response: HttpResponse = httpClient.get("/api/v1/location/$id/details") {
                parameter("key", BuildConfig.API_KEY)
                parameter("location_id", id)
            }.body()

            Gson().fromJson(
                response.bodyAsText(),
                DetailsApi::class.java
            )
        } catch (e: Exception) {
            DetailsApi("", "", "", "", 0f, "")
        }
    }

}