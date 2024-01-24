package com.baltajmn.gigi.data

import com.baltajmn.gigi.core.db.datasource.FavoritesDao
import com.baltajmn.gigi.core.db.model.FavoritesDB
import com.baltajmn.gigi.core.network.datasource.NetworkGigiDatasource
import com.baltajmn.gigi.domain.model.Favorites
import com.baltajmn.gigi.domain.model.Location

interface GigiRepository {
    suspend fun getLocations(name: String, latLong: String): List<Location>
    suspend fun getNearbyLocations(latLong: String): List<Location>
    suspend fun addToFavorites(id: Int)
    suspend fun getFavoritesList(): List<Favorites>
    suspend fun updateFavoriteList(location: Location, hasToDelete: Boolean): List<Favorites>
    suspend fun removeFavorite(id: Int): List<Favorites>
}

class DefaultGigiRepository(
    private val networkGigiDatasource: NetworkGigiDatasource,
    private val favoritesDao: FavoritesDao
) : GigiRepository {

    override suspend fun getLocations(name: String, latLong: String): List<Location> {
        return networkGigiDatasource.getLocations(name = name, latLong = latLong)
            .map { it.toDomain() }
    }

    override suspend fun getNearbyLocations(latLong: String): List<Location> {
        return networkGigiDatasource.getNearbyLocations(latLong = latLong).map { it.toDomain() }
    }

    override suspend fun addToFavorites(id: Int) {
        favoritesDao.insertFavorite(FavoritesDB(id = id, name = ""))
    }

    override suspend fun getFavoritesList(): List<Favorites> {
        favoritesDao.getFavoritesList()?.let { favoritesDBList ->
            return favoritesDBList.map { it.toDomain() }
        } ?: return emptyList()
    }

    override suspend fun updateFavoriteList(
        location: Location,
        hasToDelete: Boolean
    ): List<Favorites> {
        if (hasToDelete) {
            favoritesDao.deleteFavorite(id = location.locationId)
        } else {
            favoritesDao.insertFavorite(FavoritesDB(id = location.locationId, name = location.name))
        }
        return favoritesDao.getFavoritesList()?.map { it.toDomain() } ?: emptyList()
    }

    override suspend fun removeFavorite(id: Int): List<Favorites> {
        favoritesDao.deleteFavorite(id = id)
        return favoritesDao.getFavoritesList()?.map { it.toDomain() } ?: emptyList()
    }

}