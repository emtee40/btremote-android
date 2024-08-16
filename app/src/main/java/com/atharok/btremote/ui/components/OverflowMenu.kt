package com.atharok.btremote.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.HelpOutline
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.BrightnessHigh
import androidx.compose.material.icons.rounded.BrightnessLow
import androidx.compose.material.icons.rounded.ClosedCaption
import androidx.compose.material.icons.rounded.LinkOff
import androidx.compose.material.icons.rounded.PowerSettingsNew
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.ui.views.remoteButtons.StatefulRemoteButton

@Composable
private fun OverflowMenu(
    topBarAction: @Composable (showMenu: () -> Unit) -> Unit,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    var showMenu by remember { mutableStateOf(false) }

    topBarAction { showMenu = !showMenu }

    DropdownMenu(
        expanded = showMenu,
        onDismissRequest = { showMenu = false }
    ) {
        content { showMenu = false }
    }
}

// ---- DropdownMenu ----

@Composable
fun MoreOverflowMenu(
    modifier: Modifier = Modifier,
    content: @Composable (closeDropdownMenu: () -> Unit) -> Unit
) {
    OverflowMenu(
        topBarAction = {
            MoreAction(
                showMenu = it,
                modifier = modifier
            )
        },
        content = content
    )
}

// ---- DropdownMenuItem ----

@Composable
private fun DefaultDropdownMenuItem(
    onClick: () -> Unit,
    image: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    DropdownMenuItem(
        text = {
            TextNormal(title)
        },
        onClick = onClick,
        modifier = modifier,
        leadingIcon = {
            Icon(
                imageVector = image,
                contentDescription = title
            )
        }
    )
}

@Composable
private fun DefaultDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    image: ImageVector,
    title: String,
    modifier: Modifier = Modifier
) {
    StatefulRemoteButton(
        touchDown = touchDown,
        touchUp = touchUp
    ) {
        DropdownMenuItem(
            text = {
                TextNormal(title)
            },
            onClick = {},
            modifier = modifier,
            leadingIcon = {
                Icon(
                    imageVector = image,
                    contentDescription = title
                )
            },
            interactionSource = it
        )
    }
}

@Composable
fun PowerDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        touchDown = touchDown,
        touchUp = touchUp,
        image = Icons.Rounded.PowerSettingsNew,
        title = stringResource(id = R.string.power),
        modifier = modifier
    )
}

@Composable
fun MenuDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        touchDown = touchDown,
        touchUp = touchUp,
        image = Icons.AutoMirrored.Rounded.List,
        title = stringResource(id = R.string.menu),
        modifier = modifier
    )
}

@Composable
fun ClosedCaptionDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        touchDown = touchDown,
        touchUp = touchUp,
        image = Icons.Rounded.ClosedCaption,
        title = stringResource(id = R.string.closed_captions),
        modifier = modifier
    )
}

@Composable
fun BrightnessIncDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        touchDown = touchDown,
        touchUp = touchUp,
        image = Icons.Rounded.BrightnessHigh,
        title = stringResource(id = R.string.brightness_increase),
        modifier = modifier
    )
}

@Composable
fun BrightnessDecDropdownMenuItem(
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        touchDown = touchDown,
        touchUp = touchUp,
        image = Icons.Rounded.BrightnessLow,
        title = stringResource(id = R.string.brightness_decrease),
        modifier = modifier
    )
}

@Composable
fun DisconnectDropdownMenuItem(
    disconnect: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        onClick = disconnect,
        image = Icons.Rounded.LinkOff,
        title = stringResource(id = R.string.disconnect),
        modifier = modifier
    )
}

@Composable
fun HelpDropdownMenuItem(
    showHelp: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        onClick = showHelp,
        image = Icons.AutoMirrored.Rounded.HelpOutline,
        title = stringResource(id = R.string.help),
        modifier = modifier
    )
}

@Composable
fun SettingsDropdownMenuItem(
    showSettingsScreen: () -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultDropdownMenuItem(
        onClick = showSettingsScreen,
        image = Icons.Rounded.Settings,
        title = stringResource(id = R.string.settings),
        modifier = modifier
    )
}