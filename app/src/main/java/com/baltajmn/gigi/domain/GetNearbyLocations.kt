package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.model.Location

interface GetNearbyLocationsUseCase {
    suspend operator fun invoke(latLong: String): List<Location>
}

class GetNearbyLocations(
    private val gigiRepository: GigiRepository
) : GetNearbyLocationsUseCase {

    override suspend fun invoke(latLong: String): List<Location> {
        return gigiRepository.getNearbyLocations(latLong = latLong)
    }

}