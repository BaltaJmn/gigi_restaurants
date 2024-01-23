package com.baltajmn.gigi.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.baltajmn.gigi.R

@Composable
fun LoadingView(
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        LottieImage(
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center),
            animation = R.raw.loading
        )
    }
}