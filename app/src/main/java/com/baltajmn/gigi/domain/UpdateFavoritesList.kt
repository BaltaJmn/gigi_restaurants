package com.baltajmn.gigi.domain

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.model.Favorites
import com.baltajmn.gigi.domain.model.Location

interface UpdateFavoritesListUseCase {
    suspend operator fun invoke(location: Location, hasToDelete: Boolean): List<Favorites>
}

class UpdateFavoritesList(
    private val gigiRepository: GigiRepository
) : UpdateFavoritesListUseCase {

    override suspend fun invoke(location: Location, hasToDelete: Boolean): List<Favorites> {
        return gigiRepository.updateFavoriteList(location = location, hasToDelete = hasToDelete)
    }

}