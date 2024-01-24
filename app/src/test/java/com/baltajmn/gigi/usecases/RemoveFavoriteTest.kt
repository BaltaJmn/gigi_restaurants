package com.baltajmn.gigi.usecases

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.RemoveFavorite
import com.baltajmn.gigi.domain.RemoveFavoriteUseCase
import com.baltajmn.gigi.domain.model.Favorites
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class RemoveFavoriteTest {

    private lateinit var removeFavoriteUseCase: RemoveFavoriteUseCase
    private lateinit var gigiRepository: GigiRepository

    @Before
    fun setUp() {
        gigiRepository = Mockito.mock(GigiRepository::class.java)
        removeFavoriteUseCase = RemoveFavorite(gigiRepository)
    }

    @Test
    fun `invoke should return updated favorites list after removing a favorite`() = runBlocking {
        // Given
        val idToRemove = 42
        val originalFavoritesList = listOf(
            Favorites(id = 41, name = "Favorite 1"),
            Favorites(id = 42, name = "Favorite 2"),
            Favorites(id = 43, name = "Favorite 3")
        )

        val expectedFavoritesList = listOf(
            Favorites(id = 41, name = "Favorite 1"),
            Favorites(id = 43, name = "Favorite 3")
        )

        // Mocking repository behavior
        whenever(gigiRepository.removeFavorite(idToRemove)).thenReturn(expectedFavoritesList)

        // When
        val result = removeFavoriteUseCase.invoke(idToRemove)

        // Then
        Assert.assertEquals(expectedFavoritesList, result)
    }
}