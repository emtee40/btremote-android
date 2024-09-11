package com.atharok.btremote.ui.views.remoteButtons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.KeyboardArrowRight
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material.icons.rounded.KeyboardArrowDown
import androidx.compose.material.icons.rounded.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.ArcShape
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.domain.entity.remoteInput.RemoteInput

private val TopArcShape = ArcShape(-45f, -90f)
private val BottomArcShape = ArcShape(45f, 90f)
private val LeftArcShape = ArcShape(135f, 90f)
private val RightArcShape = ArcShape(-45f, 90f)

@Composable
fun DirectionalButtons(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.surfaceContainerLow,
    shadowElevation: Dp = dimensionResource(id = R.dimen.elevation)
) {
    Box(
        modifier = modifier.shadow(
            elevation = shadowElevation,
            shape = CircleShape
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            // ---- Top ----
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = TopArcShape,
                color = color
            ) {
                DirectionalButton(sendReport = sendRemoteKeyReport, bytes = RemoteInput.REMOTE_INPUT_MENU_UP)
            }

            // ---- Bottom ----
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = BottomArcShape,
                color = color
            ) {
                DirectionalButton(sendReport = sendRemoteKeyReport, bytes = RemoteInput.REMOTE_INPUT_MENU_DOWN)
            }

            // ---- Left ----
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = LeftArcShape,
                color = color
            ) {
                DirectionalButton(sendReport = sendRemoteKeyReport, bytes = RemoteInput.REMOTE_INPUT_MENU_LEFT)
            }

            // ---- Right ----
            Surface(
                modifier = Modifier.fillMaxSize(),
                shape = RightArcShape,
                color = color
            ) {
                DirectionalButton(sendReport = sendRemoteKeyReport, bytes = RemoteInput.REMOTE_INPUT_MENU_RIGHT)
            }

            // ---- Center ----
            Surface(
                modifier = Modifier.fillMaxSize(0.3333f),
                shape = CircleShape,
                color = color
            ) {
                DirectionalButton(sendReport = sendRemoteKeyReport, bytes = RemoteInput.REMOTE_INPUT_MENU_PICK)
            }

        }

        // ---- Icons ----
        Column(modifier = Modifier.wrapContentSize()) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(Modifier.weight(1f).padding(8.dp))
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = Icons.Rounded.KeyboardArrowUp,
                    contentDescription = stringResource(id = R.string.up),
                )
                Spacer(Modifier.weight(1f).padding(8.dp))
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.left)
                )
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = Icons.Outlined.Circle,
                    contentDescription = stringResource(id = R.string.pick)
                )
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = Icons.AutoMirrored.Rounded.KeyboardArrowRight,
                    contentDescription = stringResource(id = R.string.right)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth().height(0.dp).weight(1f),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Spacer(Modifier.weight(1f).padding(8.dp))
                Icon(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    imageVector = Icons.Rounded.KeyboardArrowDown,
                    contentDescription = stringResource(id = R.string.down)
                )
                Spacer(Modifier.weight(1f).padding(8.dp))
            }
        }
    }
}

@Composable
private fun DirectionalButton(
    bytes: ByteArray,
    sendReport: (bytes: ByteArray) -> Unit
) {
    StatefulRemoteButton(
        touchDown = { sendReport(bytes) },
        touchUp = { sendReport(REMOTE_INPUT_NONE) }
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxSize()
                .clipToBounds()
                .clickable(
                    interactionSource = it,
                    indication = LocalIndication.current,
                    onClick = {}
                )
        )
    }
}