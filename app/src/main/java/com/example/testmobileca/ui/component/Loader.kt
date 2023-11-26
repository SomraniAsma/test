package com.example.testmobileca.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun Loader(isLoading: Boolean) {
    return AnimatedVisibility(
        visible = isLoading,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(1f)
                .background(Color.Gray.copy(0.3f))
        ) {
            CircularProgressIndicator(
                modifier = Modifier.align(alignment = Alignment.Center),
                strokeWidth = 2.dp,
                color = Color.Red.copy(1f)
            )
        }
    }
}
