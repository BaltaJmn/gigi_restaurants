package com.baltajmn.gigi.usecases

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.UpdateFavoritesList
import com.baltajmn.gigi.domain.UpdateFavoritesListUseCase
import com.baltajmn.gigi.domain.model.Address
import com.baltajmn.gigi.domain.model.Favorites
import com.baltajmn.gigi.domain.model.Location
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class UpdateFavoritesListTest {

    private lateinit var updateFavoritesListUseCase: UpdateFavoritesListUseCase
    private lateinit var gigiRepository: GigiRepository

    @Before
    fun setUp() {
        gigiRepository = Mockito.mock(GigiRepository::class.java)
        updateFavoritesListUseCase = UpdateFavoritesList(gigiRepository)
    }

    @Test
    fun `invoke should update favorites list based on location and deletion flag`() = runBlocking {
        // Given
        val location = Location(
            locationId = 1,
            name = "Updated Location",
            distance = "2.0 km",
            rating = "4.8",
            bearing = "SE",
            addressObj = Address(
                street1 = "Updated Street 1",
                street2 = "Updated Street 2",
                city = "Updated City",
                state = "Updated State",
                country = "Updated Country",
                postalcode = "54321",
                addressString = "Updated Full Address",
                phone = "987-654-3210",
                latitude = 12.345,
                longitude = 78.910,
                error = null
            )
        )
        val hasToDelete = true

        val updatedFavoritesList = listOf(
            Favorites(id = 1, name = "Updated Favorite 1"),
            Favorites(id = 2, name = "Updated Favorite 2")
        )

        // Mocking repository behavior
        whenever(gigiRepository.updateFavoriteList(location, hasToDelete)).thenReturn(updatedFavoritesList)

        // When
        val result = updateFavoritesListUseCase.invoke(location, hasToDelete)

        // Then
        Assert.assertEquals(updatedFavoritesList, result)

        val locationCaptor = argumentCaptor<Location>()
        val hasToDeleteCaptor = argumentCaptor<Boolean>()

        verify(gigiRepository).updateFavoriteList(locationCaptor.capture(), hasToDeleteCaptor.capture())

        Assert.assertEquals(location, locationCaptor.firstValue)
        Assert.assertEquals(hasToDelete, hasToDeleteCaptor.firstValue)
    }
}