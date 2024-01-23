package com.baltajmn.gigi.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baltajmn.gigi.domain.GetNearbyLocationsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getNearbyLocations: GetNearbyLocationsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    init {
        viewModelScope.launch {
            val nearbyLocations = getNearbyLocations()
        }
    }

}

data class HomeState(
    val loading: Boolean = true
)