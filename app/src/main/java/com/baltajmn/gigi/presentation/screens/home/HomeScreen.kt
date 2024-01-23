package com.baltajmn.gigi.presentation.screens.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baltajmn.gigi.ui.components.LoadingView

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = HomeViewModel(),
    listState: LazyListState,
    navigateToDetails: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = state.loading,
        label = "",
    ) {
        when (it) {
            true -> LoadingView()
            false -> HomeContent(
                listState = listState,
                state = state,
                viewModel = viewModel,
                navigateToDetails = navigateToDetails
            )
        }
    }
}

@Composable
fun HomeContent(
    listState: LazyListState,
    state: HomeState,
    viewModel: HomeViewModel,
    navigateToDetails: (Int) -> Unit
) {
}