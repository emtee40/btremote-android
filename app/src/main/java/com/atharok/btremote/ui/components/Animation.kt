package com.atharok.btremote.ui.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

private const val ANIMATED_CONTENT_DURATION = 250

@Composable
fun <T> FadeAnimatedContent(
    targetState: T,
    content: @Composable (targetState: T) -> Unit
) {
    AnimatedContent(
        targetState = targetState,
        transitionSpec = {
            fadeIn(
                animationSpec = tween(ANIMATED_CONTENT_DURATION),
            ) togetherWith fadeOut(
                animationSpec = tween(ANIMATED_CONTENT_DURATION)
            )
        },
        contentAlignment = Alignment.Center,
        label = "FadeAnimatedContent"
    ) { currentTargetState: T ->
        content(currentTargetState)
    }
}