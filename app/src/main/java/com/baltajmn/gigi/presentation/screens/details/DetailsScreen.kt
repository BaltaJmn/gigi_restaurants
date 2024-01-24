package com.baltajmn.gigi.presentation.screens.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.baltajmn.gigi.ui.components.LoadingView
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    onBackPressed: () -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    AnimatedContent(
        targetState = state.loading,
        label = "",
    ) {
        when (it) {
            true -> LoadingView()
            false -> DetailsContent(
                state = state,
                onBackPressed = onBackPressed
            )
        }
    }
}

@Composable
fun DetailsContent(
    state: DetailsState,
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back",
                modifier = Modifier.clickable { onBackPressed.invoke() }
            )
        }

        state.details?.let {
            if (it.name.isNotBlank()) Text(text = "Name: ${it.name}")
            if (it.description.isNotBlank()) Text(text = "Description: ${it.description}")
            if (it.email.isNotBlank()) Text(text = "Email: ${it.email}")
            if (it.phone.isNotBlank()) Text(text = "Phone: ${it.phone}")
            if (it.rating.isNotBlank()) Text(text = "Rating: ${it.rating}")
            if (it.ratingUrl.isNotBlank()) AsyncImage(
                model = it.ratingUrl,
                contentDescription = it.ratingUrl
            )
        }
    }
}