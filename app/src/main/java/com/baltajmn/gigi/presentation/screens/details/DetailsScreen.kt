package com.baltajmn.gigi.presentation.screens.details

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = koinViewModel(),
    listState: LazyListState,
    onBackPressed: () -> Unit
) {

}