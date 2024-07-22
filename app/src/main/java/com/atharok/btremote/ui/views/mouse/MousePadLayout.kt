package com.atharok.btremote.ui.views.mouse


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDownward
import androidx.compose.material.icons.rounded.ArrowUpward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.MouseInput
import kotlin.jvm.internal.Ref.BooleanRef
import kotlin.jvm.internal.Ref.FloatRef

@Composable
fun MousePadLayout(
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    sendMouseInput: (MouseInput, Float, Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    var mouseInput: MouseInput = MouseInput.NONE
    var mouseX = 0f
    var mouseY = 0f
    var mouseWheel = 0f

    val mouseSpeedRef = remember { FloatRef() }
    mouseSpeedRef.element = mouseSpeed

    val shouldInvertMouseScrollingDirectionRef = remember { BooleanRef() }
    shouldInvertMouseScrollingDirectionRef.element = shouldInvertMouseScrollingDirection

    Column(modifier) {
        Row(
            modifier = Modifier
                .weight(0.8f)
                .padding(bottom = dimensionResource(id = R.dimen.padding_thin))
        ) {
            MousePad(
                mouseSpeed = mouseSpeedRef,
                updateMouseInput = {
                    mouseInput = it
                    sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    if (it == MouseInput.PAD_TAP) {
                        mouseInput = MouseInput.NONE
                        sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                    }
                },
                updateTouchPosition = { x: Float, y: Float ->
                    mouseX = x
                    mouseY = y
                    sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                },
                updateWheel = { wheel: Float ->
                    mouseInput = MouseInput.MOUSE_WHEEL
                    mouseWheel = wheel * if(shouldInvertMouseScrollingDirectionRef.element) -1f else 1f
                    sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
                },
                shape = RoundedCornerShape(
                    topStart = dimensionResource(id = R.dimen.card_corner_radius),
                    topEnd = 0.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 0.dp
                ),
                modifier = Modifier
                    .weight(0.87f)
                    .padding(end = dimensionResource(id = R.dimen.padding_thin))
            )

            ScrollMouseButtonsLayout(
                sendMouseInput = sendMouseInput,
                modifier = Modifier
                    .weight(0.13f)
                    .padding(start = dimensionResource(id = R.dimen.padding_thin))
            )
        }

        MouseButtonsLayout(
            sendMouseInput = {
                mouseInput = it
                sendMouseInput(mouseInput, mouseX, mouseY, mouseWheel)
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .padding(top = dimensionResource(id = R.dimen.padding_thin))
        )
    }
}

// ---- Mouse buttons ----

@Composable
private fun MouseButtonsLayout(
    sendMouseInput: (MouseInput) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {

        // Start
        MouseButton(
            touchDown = { sendMouseInput(MouseInput.MOUSE_CLICK_LEFT) },
            touchUp = { sendMouseInput(MouseInput.NONE) },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp,
                bottomStart = dimensionResource(id = R.dimen.card_corner_radius)
            ),
            modifier = Modifier
                .weight(0.38f)
                .fillMaxHeight()
                .padding(end = dimensionResource(id = R.dimen.padding_thin))
        )

        // Center
        MouseButton(
            touchDown = { sendMouseInput(MouseInput.MOUSE_CLICK_MIDDLE) },
            touchUp = { sendMouseInput(MouseInput.NONE) },
            shape = RectangleShape,
            modifier = Modifier
                .weight(0.24f)
                .fillMaxHeight()
                .padding(
                    start = dimensionResource(id = R.dimen.padding_thin),
                    end = dimensionResource(id = R.dimen.padding_thin)
                )
        )

        // End
        MouseButton(
            touchDown = { sendMouseInput(MouseInput.MOUSE_CLICK_RIGHT) },
            touchUp = { sendMouseInput(MouseInput.NONE) },
            shape = RoundedCornerShape(
                topStart = 0.dp,
                topEnd = 0.dp,
                bottomEnd = dimensionResource(id = R.dimen.card_corner_radius),
                bottomStart = 0.dp
            ),
            modifier = Modifier
                .weight(0.38f)
                .fillMaxHeight()
                .padding(start = dimensionResource(id = R.dimen.padding_thin))
        )
    }
}

// ---- Scroll Up/Down buttons ----

@Composable
private fun ScrollMouseButtonsLayout(
    sendMouseInput: (MouseInput, Float, Float, Float) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ScrollMouseButton(
            direction = 1f,
            sendMouseInput = sendMouseInput,
            image = Icons.Rounded.ArrowUpward,
            contentDescription = stringResource(id = R.string.mouse_wheel_up),
            shape = RoundedCornerShape(topEnd = dimensionResource(id = R.dimen.card_corner_radius)),
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(bottom = dimensionResource(id = R.dimen.padding_thin))
        )

        ScrollMouseButton(
            direction = -1f,
            sendMouseInput = sendMouseInput,
            image = Icons.Rounded.ArrowDownward,
            contentDescription = stringResource(id = R.string.mouse_wheel_down),
            shape = RectangleShape,
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.5f)
                .padding(top = dimensionResource(id = R.dimen.padding_thin))
        )
    }
}