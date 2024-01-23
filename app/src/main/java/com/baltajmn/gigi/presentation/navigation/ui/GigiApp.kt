package com.baltajmn.gigi.presentation.navigation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.baltajmn.gigi.presentation.navigation.graphs.GRAPH
import com.baltajmn.gigi.presentation.navigation.graphs.MainGraph
import com.baltajmn.gigi.presentation.screens.details.DetailsScreen
import com.baltajmn.gigi.presentation.screens.home.HomeScreen
import com.baltajmn.gigi.ui.theme.GigiTheme

@Composable
fun GigiApp(appState: AppState = rememberAppState()) {
    GigiTheme {
        GigiNavHost(
            appState = appState
        )
    }
}

@Composable
fun GigiNavHost(
    appState: AppState
) {
    NavHost(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
        navController = appState.mainNavController,
        route = GRAPH.Main,
        startDestination = MainGraph.Home.route
    ) {
        composable(
            route = MainGraph.Home.route
        ) {
            HomeScreen(
                listState = rememberLazyListState(),
                navigateToDetails = { id: Int -> appState.navigateToDetails(id) }
            )
        }

        composable(
            route = MainGraph.Details.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) {
            DetailsScreen(
                listState = rememberLazyListState(),
                onBackPressed = { appState.navigateUp() }
            )
        }
    }
}