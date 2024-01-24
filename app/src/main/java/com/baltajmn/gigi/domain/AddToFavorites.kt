package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository

interface AddToFavoritesUseCase {
    suspend operator fun invoke(id: Int)
}

class AddToFavorites(
    private val gigiRepository: GigiRepository
) : AddToFavoritesUseCase {

    override suspend fun invoke(id: Int) {
        return gigiRepository.addToFavorites(id = id)
    }

}