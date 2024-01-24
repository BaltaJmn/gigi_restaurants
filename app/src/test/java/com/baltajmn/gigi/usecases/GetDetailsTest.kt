package com.baltajmn.gigi.usecases

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.GetDetails
import com.baltajmn.gigi.domain.GetDetailsUseCase
import com.baltajmn.gigi.domain.model.Details
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetDetailsTest {

    private lateinit var getDetailsUseCase: GetDetailsUseCase
    private lateinit var gigiRepository: GigiRepository

    @Before
    fun setUp() {
        gigiRepository = Mockito.mock(GigiRepository::class.java)
        getDetailsUseCase = GetDetails(gigiRepository)
    }

    @Test
    fun `invoke should return details from repository`() = runBlocking {
        // Given
        val id = "123"
        val expectedDetails = Details(
            name = "Example Name",
            description = "Example Description",
            email = "",
            phone = "",
            rating = "",
            ratingUrl = ""
        )

        // Mocking repository behavior
        whenever(gigiRepository.getDetails(id)).thenReturn(expectedDetails)

        // When
        val result = getDetailsUseCase.invoke(id)

        // Then
        Assert.assertEquals(expectedDetails, result)
    }

}