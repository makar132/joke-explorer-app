package com.example.jokeexplorer.presentation.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jokeexplorer.util.shimmerBackground
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout
import com.valentinilk.shimmer.shimmer

@Composable
fun SkeletonRectangle(height: Dp, modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Color.LightGray.copy(alpha = 0.2f))
            .height(height)
            .shimmer()
            //.shimmerBackground()
    )


}
