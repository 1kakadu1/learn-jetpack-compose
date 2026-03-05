package com.example.entertainhub.ui.theme.modifiers

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.shimmerEffect(): Modifier = composed {
    val transition = rememberInfiniteTransition(label = "shimmer")

    val translateAnimation by transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1200,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmerAnimation"
    )

    val shimmerColors = listOf(
        Color(0xFF2A2A2A),
        Color(0xFF3D3D3D),
        Color(0xFF2A2A2A),
    )

    background(
        Brush.linearGradient(
            colors = shimmerColors,
            start = Offset(x = translateAnimation - 200f, y = 0f),
            end = Offset(x = translateAnimation, y = translateAnimation)
        )
    )
}