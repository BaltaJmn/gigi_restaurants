package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.model.Location

interface GetLocationsUseCase {
    suspend operator fun invoke(name: String, latLong: String): List<Location>
}

class GetLocations(
    private val gigiRepository: GigiRepository
) : GetLocationsUseCase {

    override suspend fun invoke(name: String, latLong: String): List<Location> {
        return gigiRepository.getLocations(name = name, latLong = latLong)
    }

}