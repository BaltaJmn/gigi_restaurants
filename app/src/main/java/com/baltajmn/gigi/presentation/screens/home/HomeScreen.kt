package com.baltajmn.gigi.presentation.screens.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.baltajmn.gigi.core.common.components.ComposableLifecycle
import com.baltajmn.gigi.domain.model.Location
import com.baltajmn.gigi.presentation.components.NearContent
import com.baltajmn.gigi.ui.components.LoadingView
import com.google.android.gms.location.LocationServices
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    listState: LazyListState,
    navigateToDetails: (Int) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                getCurrentLocation(context) { latitude, longitude ->
                    viewModel.getNearbyLocationsWithCoordinates(
                        latitude = latitude,
                        longitude = longitude
                    )
                }
            } else {
                viewModel.stopLoading()
            }
        }
    )

    ComposableLifecycle { _, event ->
        when (event) {
            Lifecycle.Event.ON_START -> {
                if (hasLocationPermission(context)) {
                    getCurrentLocation(context) { latitude, longitude ->
                        viewModel.getNearbyLocationsWithCoordinates(
                            latitude = latitude,
                            longitude = longitude
                        )
                    }
                } else {
                    requestPermissionLauncher.launch(android.Manifest.permission.ACCESS_FINE_LOCATION)
                }
            }

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
    var query by remember { mutableStateOf(state.searchQuery) }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item { Text(text = "Welcome to Gigi", style = MaterialTheme.typography.titleLarge) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Text(text = "Restaurants near you", style = MaterialTheme.typography.bodyMedium) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            if (state.coordinatesRestaurant.isNotEmpty()) {
                NearContent(
                    state = state,
                    onFavoriteClicked = { viewModel.addLocationToFavorites(it) },
                    navigateToDetails = navigateToDetails
                )
            } else {
                Text(
                    text = "No restaurants found or you don't have the location permission",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item { Text(text = "Search restaurants", style = MaterialTheme.typography.bodyMedium) }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        item {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = query,
                onValueChange = {
                    query = it
                    if (it.isNotBlank()) {
                        viewModel.updateSearchQuery(it)
                    }
                }
            )
        }
        item { Spacer(modifier = Modifier.height(8.dp)) }
        items(state.searchedRestaurant) { location ->
            LocationRow(
                location = location,
                isFavorite = state.favoriteRestaurant.map { it.id }
                    .any { it == location.locationId },
                onFavoriteClicked = { viewModel.addLocationToFavorites(it) },
                navigateToDetails = navigateToDetails
            )
        }
    }
}

@Composable
fun LocationRow(
    location: Location,
    isFavorite: Boolean = false,
    onFavoriteClicked: (Location) -> Unit,
    navigateToDetails: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clickable { navigateToDetails.invoke(location.locationId) },
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = location.name,
            style = MaterialTheme.typography.bodyMedium,
        )

        Icon(
            imageVector = Icons.Filled.Star,
            tint = if (isFavorite) MaterialTheme.colorScheme.primary else Color.Gray,

            contentDescription = "Favorite",
            modifier = Modifier.clickable {
                onFavoriteClicked(location)
            }
        )
    }
}

private fun hasLocationPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}

@SuppressLint("MissingPermission")
private fun getCurrentLocation(context: Context, onPermissionGranted: (String, String) -> Unit) {
    LocationServices.getFusedLocationProviderClient(context).lastLocation
        .addOnSuccessListener { location ->
            if (location != null) {
                onPermissionGranted.invoke(
                    location.latitude.toString(),
                    location.longitude.toString()
                )
            }
        }
        .addOnFailureListener { exception ->
            exception.printStackTrace()
        }
}