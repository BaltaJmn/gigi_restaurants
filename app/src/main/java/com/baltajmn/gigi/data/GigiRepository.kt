package com.baltajmn.gigi.data

import com.baltajmn.gigi.core.network.datasource.NetworkGigiDatasource

interface GigiRepository {
    suspend fun getNearbyLocations(latLong: String): String
}

class DefaultGigiRepository(
    private val networkGigiDatasource: NetworkGigiDatasource
) : GigiRepository {

    override suspend fun getNearbyLocations(latLong: String): String {
        return networkGigiDatasource.getNearbyLocations(latLong = latLong)
    }

}