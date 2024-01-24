package com.baltajmn.gigi

import com.baltajmn.gigi.core.db.datasource.FavoritesDao
import com.baltajmn.gigi.core.db.model.FavoritesDB
import com.baltajmn.gigi.core.network.datasource.NetworkGigiDatasource
import com.baltajmn.gigi.core.network.model.AddressObj
import com.baltajmn.gigi.core.network.model.DetailsApi
import com.baltajmn.gigi.core.network.model.LocationApi
import com.baltajmn.gigi.data.DefaultGigiRepository
import com.baltajmn.gigi.data.toDomain
import com.baltajmn.gigi.domain.model.Address
import com.baltajmn.gigi.domain.model.Details
import com.baltajmn.gigi.domain.model.Favorites
import com.baltajmn.gigi.domain.model.Location
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito

class DefaultGigiRepositoryTest {

    private lateinit var defaultGigiRepository: DefaultGigiRepository
    private lateinit var networkGigiDatasource: NetworkGigiDatasource
    private lateinit var favoritesDao: FavoritesDao

    @Before
    fun setUp() {
        networkGigiDatasource = Mockito.mock(NetworkGigiDatasource::class.java)
        favoritesDao = Mockito.mock(FavoritesDao::class.java)
        defaultGigiRepository = DefaultGigiRepository(networkGigiDatasource, favoritesDao)
    }

    @Test
    fun `getLocations should return list of locations from network`() = runBlocking {
        // Given
        val name = "ExampleName"
        val latLong = "12.3456,78.9101"
        val networkLocations = listOf(
            LocationApi(
                locationId = 1,
                name = "Location 1",
                distance = "0.17",
                rating = "4.5",
                bearing = "SE",
                addressObj = AddressObj(
                    street1 = null,
                    street2 = null,
                    city = null,
                    state = null,
                    country = null,
                    postalcode = null,
                    addressString = "Address 1",
                    phone = null,
                    latitude = 12.345,
                    longitude = 78.910,
                    error = null
                )
            ),
            LocationApi(
                locationId = 2,
                name = "Location 2",
                distance = "0.17",
                rating = "4.5",
                bearing = "SE",
                addressObj = AddressObj(
                    street1 = null,
                    street2 = null,
                    city = null,
                    state = null,
                    country = null,
                    postalcode = null,
                    addressString = "Address 1",
                    phone = null,
                    latitude = 12.345,
                    longitude = 78.910,
                    error = null
                )
            ),
        )

        // Mocking network datasource behavior
        whenever(networkGigiDatasource.getLocations(name, latLong)).thenReturn(networkLocations)

        // When
        val result = defaultGigiRepository.getLocations(name, latLong)

        // Then
        Assert.assertEquals(networkLocations.map { it.toDomain() }, result)
    }

    @Test
    fun `addToFavorites should insert favorite into database`() = runBlocking {
        // Given
        val idToAdd = 42

        // When
        defaultGigiRepository.addToFavorites(idToAdd)

        // Then
        val favoriteCaptor = argumentCaptor<FavoritesDB>()
        verify(favoritesDao).insertFavorite(favoriteCaptor.capture())

        Assert.assertEquals(idToAdd, favoriteCaptor.firstValue.id)
    }

    @Test
    fun `getFavoritesList should return empty list if database is empty`() = runBlocking {
        // Mocking database behavior
        whenever(favoritesDao.getFavoritesList()).thenReturn(null)

        // When
        val result = defaultGigiRepository.getFavoritesList()

        // Then
        Assert.assertEquals(emptyList<Favorites>(), result)
    }

    @Test
    fun `removeFavorite should delete favorite from database`() = runBlocking {
        // Given
        val idToRemove = 42

        // When
        defaultGigiRepository.removeFavorite(idToRemove)

        // Then
        verify(favoritesDao).deleteFavorite(idToRemove)
    }

    @Test
    fun `updateFavoriteList should insert or delete favorite based on flag`() = runBlocking {
        // Given
        val location = Location(
            locationId = 1,
            name = "Location 1",
            distance = "0.17",
            rating = "4.5",
            bearing = "SE",
            addressObj = Address(
                street1 = "Street 1",
                street2 = "",
                city = "",
                state = "",
                country = "",
                postalcode = "",
                addressString = "Address 1",
                phone = "",
                latitude = 12.345,
                longitude = 78.910,
                error = null
            )
        )

        val hasToDelete = true

        // When
        defaultGigiRepository.updateFavoriteList(location, hasToDelete)

        // Then
        if (hasToDelete) {
            verify(favoritesDao).deleteFavorite(location.locationId)
        } else {
            val favoriteCaptor = argumentCaptor<FavoritesDB>()
            verify(favoritesDao).insertFavorite(favoriteCaptor.capture())

            Assert.assertEquals(location.locationId, favoriteCaptor.firstValue.id)
        }
    }

    @Test
    fun `getNearbyLocations should return list of nearby locations from network`() = runBlocking {
        // Given
        val latLong = "12.3456,78.9101"
        val networkNearbyLocations = listOf(
            LocationApi(
                locationId = 1,
                name = "Location 1",
                distance = "0.17",
                rating = "4.5",
                bearing = "SE",
                addressObj = AddressObj(
                    street1 = null,
                    street2 = null,
                    city = null,
                    state = null,
                    country = null,
                    postalcode = null,
                    addressString = "Address 1",
                    phone = null,
                    latitude = 12.345,
                    longitude = 78.910,
                    error = null
                )
            ),
            LocationApi(
                locationId = 2,
                name = "Location 2",
                distance = "0.17",
                rating = "4.5",
                bearing = "SE",
                addressObj = AddressObj(
                    street1 = null,
                    street2 = null,
                    city = null,
                    state = null,
                    country = null,
                    postalcode = null,
                    addressString = "Address 1",
                    phone = null,
                    latitude = 12.345,
                    longitude = 78.910,
                    error = null
                )
            ),
        )

        // Mocking network datasource behavior
        whenever(networkGigiDatasource.getNearbyLocations(latLong)).thenReturn(
            networkNearbyLocations
        )

        // When
        val result = defaultGigiRepository.getNearbyLocations(latLong)

        // Then
        Assert.assertEquals(networkNearbyLocations.map { it.toDomain() }, result)
    }

    @Test
    fun `getDetails should return details from network`() = runBlocking {
        // Given
        val id = "12345"
        val networkDetails = DetailsApi(
            name = "Location 1",
            description = "Description 1",
            email = "",
            phone = "",
            rating = 4.5f,
            ratingImageUrl = ""
        )

        // Mocking network datasource behavior
        whenever(networkGigiDatasource.getDetails(id)).thenReturn(networkDetails)

        // When
        val result = defaultGigiRepository.getDetails(id)

        // Then
        Assert.assertEquals(networkDetails.toDomain(), result)
    }

}