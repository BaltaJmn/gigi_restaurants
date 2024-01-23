package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository

interface GetNearbyLocationsUseCase {
    suspend operator fun invoke(): String
}

class GetNearbyLocations(
    private val gigiRepository: GigiRepository
) : GetNearbyLocationsUseCase {

    override suspend fun invoke(): String {
        return gigiRepository.getNearbyLocations("")
    }

}