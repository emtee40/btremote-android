package com.atharok.btremote.ui.views.remoteButtons

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.ui.components.AdaptiveText
import com.atharok.btremote.ui.components.CircleElevatedCard

@Composable
fun DialPadButton(
    text: String,
    byteArray: ByteArray,
    sendRemoteKey: (bytes: ByteArray) -> Unit,
    modifier: Modifier = Modifier
) {
    CircleElevatedCard(
        modifier = modifier,
        shape = CircleShape
    ) {
        StatefulRemoteButton(
            touchDown = { sendRemoteKey(byteArray) },
            touchUp = { sendRemoteKey(REMOTE_INPUT_NONE) }
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
                AdaptiveText(
                    text = text,
                    percent = 0.45f,
                    modifier = Modifier.fillMaxSize(),
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}