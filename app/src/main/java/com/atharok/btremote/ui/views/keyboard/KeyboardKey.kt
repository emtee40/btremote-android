package com.atharok.btremote.ui.views.keyboard

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.atharok.btremote.R
import com.atharok.btremote.ui.components.DefaultElevatedCard
import com.atharok.btremote.ui.views.remoteButtons.StatefulRemoteButton

@Composable
fun KeyboardKeyView(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    DefaultElevatedCard(
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.keyboard_key_corner_radius))
    ) {
        StatefulRemoteButton(
            touchDown = touchDown,
            touchUp = touchUp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = it,
                        indication = LocalIndication.current,
                        onClick = {}
                    ),
                contentAlignment = Alignment.Center
            ) {
                content()
            }
        }
    }
}