package com.baltajmn.gigi.presentation.screens.details

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
    LazyColumn(
        state = rememberLazyListState(),
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(16.dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item { Spacer(modifier = Modifier.height(16.dp)) }
        item {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back",
                    modifier = Modifier.clickable { onBackPressed.invoke() }
                )
            }
        }

        state.details?.let {
            if (it.name.isNotBlank()) {
                item {
                    Text(text = "Name", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.name, style = MaterialTheme.typography.bodyMedium)
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }


            if (it.description.isNotBlank()) {
                item {
                    Text(text = "Description", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.description, style = MaterialTheme.typography.bodyMedium)
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }


            if (it.email.isNotBlank()) {
                item {
                    Text(text = "Email", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.email, style = MaterialTheme.typography.bodyMedium)
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }

            if (it.phone.isNotBlank()) {
                item {
                    Text(text = "Phone", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.phone, style = MaterialTheme.typography.bodyMedium)
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }


            if (it.rating.isNotBlank()) {
                item {
                    Text(text = "Rating", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.rating, style = MaterialTheme.typography.bodyMedium)
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        }
    }
}