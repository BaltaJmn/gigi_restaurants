package com.baltajmn.gigi.core.network.datasource

import com.baltajmn.gigi.core.network.model.LocationApi
import com.baltajmn.gigi.core.network.service.GigiService

interface NetworkGigiDatasource {
    suspend fun getLocations(name: String, latLong: String): List<LocationApi>
    suspend fun getNearbyLocations(latLong: String): List<LocationApi>
}

class DefaultNetworkGigiDatasource(
    private val gigiService: GigiService
) : NetworkGigiDatasource {

    override suspend fun getLocations(name: String, latLong: String): List<LocationApi> {
        return gigiService.getLocations(name = name, latLong = latLong)
    }

    override suspend fun getNearbyLocations(latLong: String): List<LocationApi> {
        return gigiService.getNearbyLocations(latLong = latLong)
    }

}