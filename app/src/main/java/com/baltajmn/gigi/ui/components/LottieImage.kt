package com.baltajmn.gigi.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun LottieImage(
    modifier: Modifier = Modifier,
    animation: Int,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(animation))
    val animationProgress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        restartOnPlay = false,
    )
    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { animationProgress },
        contentScale = ContentScale.Crop
    )
}