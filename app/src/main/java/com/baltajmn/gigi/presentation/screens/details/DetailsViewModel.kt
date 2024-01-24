package com.baltajmn.gigi.presentation.screens.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baltajmn.gigi.domain.GetDetailsUseCase
import com.baltajmn.gigi.domain.model.Details
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val getDetails: GetDetailsUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailsState())
    val uiState: StateFlow<DetailsState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loading = false,
                    details = getDetails(id = checkNotNull(savedStateHandle["id"]))
                )
            }
        }
    }
}

data class DetailsState(
    val loading: Boolean = false,
    val details: Details? = null,
)