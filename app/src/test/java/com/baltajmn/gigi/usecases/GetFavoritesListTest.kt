package com.baltajmn.gigi.usecases

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.GetFavoritesList
import com.baltajmn.gigi.domain.GetFavoritesListUseCase
import com.baltajmn.gigi.domain.model.Favorites
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class GetFavoritesListTest {

    private lateinit var getFavoritesListUseCase: GetFavoritesListUseCase
    private lateinit var gigiRepository: GigiRepository

    @Before
    fun setUp() {
        gigiRepository = Mockito.mock(GigiRepository::class.java)
        getFavoritesListUseCase = GetFavoritesList(gigiRepository)
    }

    @Test
    fun `invoke should return favorites list from repository`() = runBlocking {
        // Given
        val expectedFavoritesList = listOf(
            Favorites(id = 1, name = "Favorite 1"),
            Favorites(id = 2, name = "Favorite 2")
        )

        // Mocking repository behavior
        whenever(gigiRepository.getFavoritesList()).thenReturn(expectedFavoritesList)

        // When
        val result = getFavoritesListUseCase.invoke()

        // Then
        Assert.assertEquals(expectedFavoritesList, result)
    }
}