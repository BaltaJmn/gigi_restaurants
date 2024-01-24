package com.baltajmn.gigi.usecases

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.GetLocations
import com.baltajmn.gigi.domain.GetLocationsUseCase
import com.baltajmn.gigi.domain.model.Address
import com.baltajmn.gigi.domain.model.Location
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.whenever

class GetLocationsTest {

    private lateinit var getLocationsUseCase: GetLocationsUseCase
    private lateinit var gigiRepository: GigiRepository

    @Before
    fun setUp() {
        gigiRepository = Mockito.mock(GigiRepository::class.java)
        getLocationsUseCase = GetLocations(gigiRepository)
    }

    @Test
    fun `invoke should return locations list from repository`() = runBlocking {
        // Given
        val name = "Example Name"
        val latLong = "12.3456,78.9101"
        val expectedLocationsList = listOf(
            Location(
                locationId = 1,
                name = "Location 1",
                distance = "1.5 km",
                rating = "4.5",
                bearing = "NE",
                addressObj = Address(
                    street1 = "Street 1",
                    street2 = "Street 2",
                    city = "City",
                    state = "State",
                    country = "Country",
                    postalcode = "12345",
                    addressString = "Full Address",
                    phone = "123-456-7890",
                    latitude = 12.345,
                    longitude = 78.910,
                    error = null
                )
            )
        )

        // Mocking repository behavior
        whenever(gigiRepository.getLocations(name, latLong)).thenReturn(expectedLocationsList)

        // When
        val result = getLocationsUseCase.invoke(name, latLong)

        // Then
        Assert.assertEquals(expectedLocationsList, result)
    }
}