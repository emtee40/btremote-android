package com.atharok.btremote.ui.views.mouse

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import com.atharok.btremote.domain.entity.remoteInput.MouseAction
import com.atharok.btremote.ui.components.DefaultElevatedCard
import com.atharok.btremote.ui.views.remoteButtons.StatefulRemoteButton

@Composable
fun MouseButton(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    DefaultElevatedCard(
        modifier = modifier,
        shape = shape
    ) {
        StatefulRemoteButton(
            touchDown = touchDown,
            touchUp = touchUp
        ) { interactionSource ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clipToBounds()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = LocalIndication.current,
                        onClick = {}
                    )
            )
        }
    }
}

@Composable
fun ScrollMouseButton(
    direction: Float,
    sendMouseInput: (MouseAction, Float, Float, Float) -> Unit,
    image: ImageVector,
    contentDescription: String,
    shape: Shape,
    modifier: Modifier = Modifier
) {
    DefaultElevatedCard(
        modifier = modifier,
        shape = shape
    ) {
        StatefulRemoteButton(
            touchDown = { sendMouseInput(MouseAction.MOUSE_WHEEL, 0f, 0f, direction) },
            touchUp = { sendMouseInput(MouseAction.NONE, 0f, 0f, 0f) }
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
                Icon(
                    imageVector = image,
                    contentDescription = contentDescription,
                    modifier = Modifier.fillMaxSize(0.50f)
                )
            }
        }
    }
}