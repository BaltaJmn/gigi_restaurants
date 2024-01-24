package com.baltajmn.gigi.presentation.screens.favorites

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baltajmn.gigi.core.common.components.ComposableLifecycle
import com.baltajmn.gigi.domain.model.Favorites
import com.baltajmn.gigi.ui.components.LoadingView
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel = koinViewModel(),
    listState: LazyListState,
    navigateToDetails: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                viewModel.refreshFavorites()
            }

            else -> {}
        }
    }

    AnimatedContent(
        targetState = state.loading,
        label = "",
    ) {
        when (it) {
            true -> LoadingView()
            false -> FavoritesContent(
                listState = listState,
                state = state,
                viewModel = viewModel,
                navigateToDetails = navigateToDetails
            )
        }
    }
}

@Composable
fun FavoritesContent(
    listState: LazyListState,
    state: FavoritesState,
    viewModel: FavoritesViewModel,
    navigateToDetails: (Int) -> Unit
) {
    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(state.favoriteRestaurant) { favorite ->
            FavoriteItem(
                favorite = favorite,
                onRemoveClick = { viewModel.removeFavorite(favorite) },
                navigateToDetails = navigateToDetails
            )
        }
    }
}

@Composable
fun FavoriteItem(
    favorite: Favorites,
    onRemoveClick: () -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = MaterialTheme.shapes.small
            )
            .clickable { navigateToDetails.invoke(favorite.id) },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = favorite.name, modifier = Modifier.padding(8.dp))

        Icon(
            imageVector = Icons.Filled.Clear,
            contentDescription = "clear",
            modifier = Modifier.clickable {
                onRemoveClick()
            }
        )
    }
}