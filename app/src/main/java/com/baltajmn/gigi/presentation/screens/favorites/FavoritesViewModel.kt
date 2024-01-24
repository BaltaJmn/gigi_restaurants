package com.baltajmn.gigi.presentation.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baltajmn.gigi.domain.GetFavoritesListUseCase
import com.baltajmn.gigi.domain.RemoveFavoriteUseCase
import com.baltajmn.gigi.domain.model.Favorites
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val getFavoritesList: GetFavoritesListUseCase,
    private val removeFavorite: RemoveFavoriteUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(FavoritesState())
    val uiState: StateFlow<FavoritesState> = _uiState

    fun refreshFavorites() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loading = false,
                    favoriteRestaurant = getFavoritesList()
                )
            }
        }
    }

    fun removeFavorite(favorite: Favorites) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    favoriteRestaurant = removeFavorite(id = favorite.id)
                )
            }
        }
    }
}

data class FavoritesState(
    val loading: Boolean = true,

    val favoriteRestaurant: List<Favorites> = emptyList(),
)