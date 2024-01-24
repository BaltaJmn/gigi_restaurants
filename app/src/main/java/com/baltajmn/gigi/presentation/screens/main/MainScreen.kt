package com.baltajmn.gigi.presentation.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.baltajmn.gigi.presentation.screens.favorites.FavoritesScreen
import com.baltajmn.gigi.presentation.screens.home.HomeScreen

@Composable
fun MainScreen(navigateToDetails: (Int) -> Unit) {
    var tabIndex by remember { mutableIntStateOf(0) }

    val tabs = listOf("Home", "Favorites")

    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(text = { Text(title) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> HomeScreen(
                listState = rememberLazyListState(),
                navigateToDetails = navigateToDetails
            )

            1 -> FavoritesScreen(
                listState = rememberLazyListState(),
                navigateToDetails = navigateToDetails
            )
        }
    }
}