package com.baltajmn.gigi.usecases

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.GetNearbyLocations
import com.baltajmn.gigi.domain.GetNearbyLocationsUseCase
import com.baltajmn.gigi.domain.model.Address
import com.baltajmn.gigi.domain.model.Location
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetNearbyLocationsTest {

    private lateinit var getNearbyLocationsUseCase: GetNearbyLocationsUseCase
    private lateinit var gigiRepository: GigiRepository

    @Before
    fun setUp() {
        gigiRepository = Mockito.mock(GigiRepository::class.java)
        getNearbyLocationsUseCase = GetNearbyLocations(gigiRepository)
    }

    @Test
    fun `invoke should return nearby locations list from repository`() = runBlocking {
        // Given
        val latLong = "12.3456,78.9101"
        val expectedNearbyLocationsList = listOf(
            Location(
                locationId = 1,
                name = "Nearby Location 1",
                distance = "0.5 km",
                rating = "4.0",
                bearing = "NW",
                addressObj = Address(
                    street1 = "Nearby Street 1",
                    street2 = "Nearby Street 2",
                    city = "Nearby City",
                    state = "Nearby State",
                    country = "Nearby Country",
                    postalcode = "54321",
                    addressString = "Nearby Full Address",
                    phone = "987-654-3210",
                    latitude = 12.345,
                    longitude = 78.910,
                    error = null
                )
            )
        )

        // Mocking repository behavior
        whenever(gigiRepository.getNearbyLocations(latLong)).thenReturn(expectedNearbyLocationsList)

        // When
        val result = getNearbyLocationsUseCase.invoke(latLong)

        // Then
        Assert.assertEquals(expectedNearbyLocationsList, result)
    }
}