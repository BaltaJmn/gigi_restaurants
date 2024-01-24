package com.baltajmn.gigi.usecases

import com.baltajmn.gigi.data.GigiRepository
import com.baltajmn.gigi.domain.AddToFavorites
import com.baltajmn.gigi.domain.AddToFavoritesUseCase
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class AddToFavoritesTest {

    private lateinit var addToFavoritesUseCase: AddToFavoritesUseCase
    private lateinit var gigiRepository: GigiRepository

    @Before
    fun setUp() {
        gigiRepository = Mockito.mock(GigiRepository::class.java)
        addToFavoritesUseCase = AddToFavorites(gigiRepository)
    }

    @Test
    fun `invoke should call addToFavorites on repository with correct id`() = runBlocking {
        // Given
        val id = 42

        // When
        addToFavoritesUseCase.invoke(id)

        // Then
        Mockito.verify(gigiRepository).addToFavorites(id)
    }

}