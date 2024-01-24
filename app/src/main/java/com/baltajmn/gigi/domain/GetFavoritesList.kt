package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.model.Favorites

interface GetFavoritesListUseCase {
    suspend operator fun invoke(): List<Favorites>
}

class GetFavoritesList(
    private val gigiRepository: GigiRepository
) : GetFavoritesListUseCase {

    override suspend fun invoke(): List<Favorites> {
        return gigiRepository.getFavoritesList()
    }

}