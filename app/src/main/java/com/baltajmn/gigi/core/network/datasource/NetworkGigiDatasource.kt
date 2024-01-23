package com.baltajmn.gigi.core.network.datasource

import com.baltajmn.gigi.core.network.service.GigiService

interface NetworkGigiDatasource {
    suspend fun getNearbyLocations(latLong: String): String
}

class DefaultNetworkGigiDatasource(
    private val gigiService: GigiService
) : NetworkGigiDatasource {

    override suspend fun getNearbyLocations(latLong: String): String {
        return gigiService.getNearbyLocations(latLong = latLong)
    }

}