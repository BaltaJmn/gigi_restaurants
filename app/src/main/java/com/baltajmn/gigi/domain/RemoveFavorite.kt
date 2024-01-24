package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.model.Favorites

interface RemoveFavoriteUseCase {
    suspend operator fun invoke(id: Int): List<Favorites>
}

class RemoveFavorite(
    private val gigiRepository: GigiRepository
) : RemoveFavoriteUseCase {

    override suspend fun invoke(id: Int): List<Favorites> {
        return gigiRepository.removeFavorite(id = id)
    }

}