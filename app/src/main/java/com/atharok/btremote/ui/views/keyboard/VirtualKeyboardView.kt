package com.atharok.btremote.ui.views.keyboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Backspace
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.automirrored.rounded.KeyboardReturn
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ScreenshotMonitor
import androidx.compose.material.icons.rounded.SpaceBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout

@Composable
fun VirtualKeyboardView(
    mustClearInputField: Boolean,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String) -> Unit,
    showKeyboardBottomSheet: Boolean,
    onShowKeyboardBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    KeyboardModalBottomSheet(
        showKeyboardBottomSheet = showKeyboardBottomSheet,
        onShowKeyboardBottomSheetChanged = onShowKeyboardBottomSheetChanged,
        windowInsets = WindowInsets.ime,
        modifier = modifier
    ) {
        StatefulKeyboardView { focusRequester, textState ->
            StatelessKeyboardView(
                mustClearInputField = mustClearInputField,
                focusRequester = focusRequester,
                text = textState.value,
                onTextChange = {
                    textState.value = it
                },
                sendKeyboardKeyReport = sendKeyboardKeyReport,
                sendTextReport = sendTextReport,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_standard))
                    .padding(bottom = dimensionResource(id = R.dimen.padding_large))
            )
        }
    }
}

@Composable
private fun StatefulKeyboardView(
    content: @Composable (FocusRequester, textState: MutableState<String>) -> Unit
) {
    val focusRequester = remember { FocusRequester() }
    val textState = remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    content(focusRequester, textState)
}

@Composable
private fun StatelessKeyboardView(
    mustClearInputField: Boolean,
    focusRequester: FocusRequester,
    text: String,
    onTextChange: (String) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = R.dimen.padding_standard)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = onTextChange,
                modifier = Modifier
                    .weight(4f)
                    .padding(end = dimensionResource(id = R.dimen.padding_thin))
                    .focusRequester(focusRequester),
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        sendTextReport(text)
                        sendKeyboardKeyReport(VirtualKeyboardLayout.KEYBOARD_KEY_ENTER)
                        sendKeyboardKeyReport(REMOTE_INPUT_NONE)
                        if(mustClearInputField) {
                            onTextChange("")
                        }
                    }
                ),
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.keyboard_key_corner_radius)),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent
                )
            )
            IconButton(
                onClick = {
                    sendTextReport(text)
                    if(mustClearInputField) {
                        onTextChange("")
                    }
                },
                modifier = Modifier.weight(1f).padding(start = dimensionResource(id = R.dimen.padding_thin))
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.Send,
                    contentDescription = stringResource(id = R.string.send)
                )
            }
        }

        AdditionalKeyboardKeys(
            sendKeyboardKeyReport = sendKeyboardKeyReport,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(unbounded = true)
                .padding(top = dimensionResource(id = R.dimen.padding_standard))
        )
    }
}


@Composable
private fun AdditionalKeyboardKeys(
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_thin))
        ) {
            VirtualKeyboardKey(
                image = Icons.Rounded.SpaceBar,
                contentDescription = stringResource(id = R.string.space_bar),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_SPACE_BAR,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(3f).padding(end = dimensionResource(id = R.dimen.padding_thin))
            )

            VirtualKeyboardKey(
                image = Icons.Rounded.KeyboardArrowUp,
                contentDescription = stringResource(id = R.string.up),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_UP,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_thin))
            )

            VirtualKeyboardKey(
                image = Icons.Rounded.ScreenshotMonitor,
                contentDescription = stringResource(id = R.string.print_screen),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_PRINT_SCREEN,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(start = dimensionResource(id = R.dimen.padding_thin))
            )
        }

        Row(
            modifier = Modifier.padding(vertical = dimensionResource(id = R.dimen.padding_thin))
        ) {
            VirtualKeyboardKey(
                image = Icons.AutoMirrored.Rounded.Backspace,
                contentDescription = stringResource(id = R.string.delete),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_DELETE,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(end = dimensionResource(id = R.dimen.padding_thin))
            )

            VirtualKeyboardKey(
                image = Icons.AutoMirrored.Rounded.KeyboardReturn,
                contentDescription = stringResource(id = R.string.enter),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_ENTER,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_thin))
            )

            VirtualKeyboardKey(
                image = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                contentDescription = stringResource(id = R.string.left),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_LEFT,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_thin))
            )

            VirtualKeyboardKey(
                image = Icons.Rounded.KeyboardArrowDown,
                contentDescription = stringResource(id = R.string.down),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_DOWN,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(horizontal = dimensionResource(id = R.dimen.padding_thin))
            )

            VirtualKeyboardKey(
                image = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.right),
                bytes = VirtualKeyboardLayout.KEYBOARD_KEY_RIGHT,
                sendKeyboardKey = sendKeyboardKeyReport,
                modifier = Modifier.weight(1f).padding(start = dimensionResource(id = R.dimen.padding_thin))
            )
        }
    }
}

@Composable
private fun VirtualKeyboardKey(
    image: ImageVector,
    contentDescription: String,
    bytes: ByteArray,
    sendKeyboardKey: (ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    KeyboardKeyView(
        touchDown = { sendKeyboardKey(bytes) },
        touchUp = { sendKeyboardKey(REMOTE_INPUT_NONE) },
        modifier = modifier,
    ) {
        Icon(
            imageVector = image,
            contentDescription = contentDescription,
            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_standard))
        )
    }
}