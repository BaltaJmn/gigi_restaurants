package com.baltajmn.gigi.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baltajmn.gigi.domain.GetFavoritesListUseCase
import com.baltajmn.gigi.domain.GetLocationsUseCase
import com.baltajmn.gigi.domain.GetNearbyLocationsUseCase
import com.baltajmn.gigi.domain.UpdateFavoritesListUseCase
import com.baltajmn.gigi.domain.model.Favorites
import com.baltajmn.gigi.domain.model.Location
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getLocations: GetLocationsUseCase,
    private val getNearbyLocations: GetNearbyLocationsUseCase,
    private val getFavoritesList: GetFavoritesListUseCase,
    private val updateFavoritesList: UpdateFavoritesListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    private var searchJob: Job? = null

    fun refreshFavorites() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    favoriteRestaurant = getFavoritesList()
                )
            }
        }
    }

    fun getNearbyLocationsWithCoordinates(latitude: String, longitude: String) {
        if (_uiState.value.currentCoordinates.isBlank()) {
            viewModelScope.launch {

                val coordinatesRestaurant = getNearbyLocations(
                    StringBuilder().apply {
                        append(latitude)
                        append(",")
                        append(longitude)
                    }.toString()
                )

                val favoriteRestaurant = getFavoritesList()

                _uiState.update {
                    it.copy(
                        loading = false,
                        currentCoordinates = "$latitude,$longitude",
                        coordinatesRestaurant = coordinatesRestaurant,
                        favoriteRestaurant = favoriteRestaurant
                    )
                }
            }
        }
    }

    fun addLocationToFavorites(location: Location) {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    favoriteRestaurant = updateFavoritesList(
                        location = location,
                        hasToDelete = it.favoriteRestaurant.map { it.id }
                            .contains(location.locationId)
                    )
                )
            }
        }
    }

    fun updateSearchQuery(query: String) {
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _uiState.update {
                it.copy(
                    searchQuery = query,
                    searchedRestaurant = getLocations(
                        name = query,
                        latLong = it.currentCoordinates
                    )
                )
            }
        }
    }
}

data class HomeState(
    val loading: Boolean = true,

    val currentCoordinates: String = "",

    val coordinatesRestaurant: List<Location> = emptyList(),
    val searchedRestaurant: List<Location> = emptyList(),
    val favoriteRestaurant: List<Favorites> = emptyList(),

    val searchQuery: String = ""
)