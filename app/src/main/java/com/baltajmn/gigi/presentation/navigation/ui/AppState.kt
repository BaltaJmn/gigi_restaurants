package com.baltajmn.gigi.presentation.navigation.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.baltajmn.gigi.presentation.navigation.extensions.navigatePoppingUpToStartDestination
import com.baltajmn.gigi.presentation.navigation.graphs.MainGraph

@Composable
fun rememberAppState(
    mainNavController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(mainNavController, context) {
    AppState(mainNavController, context)
}

@Stable
class AppState(
    val mainNavController: NavHostController,
    private val context: Context
) {

    val currentRoute: String
        @Composable get() = mainNavController.currentBackStackEntryAsState().value?.destination?.route
            ?: ""

    fun navigateUp() {
        mainNavController.navigateUp()
    }

    fun navigateToHome() {
        mainNavController.navigatePoppingUpToStartDestination(MainGraph.Home.route)
    }

    fun navigateToDetails(id: Int) {
        mainNavController.navigate(MainGraph.Details.route.replace("{id}", id.toString()))
    }
}