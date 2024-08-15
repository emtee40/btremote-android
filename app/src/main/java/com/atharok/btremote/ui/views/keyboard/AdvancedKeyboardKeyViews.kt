package com.atharok.btremote.ui.views.keyboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.IconAdvancedKeyboardKey
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.TextAdvancedKeyboardKey
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.TextAdvancedKeyboardModifierKey
import com.atharok.btremote.ui.components.AdaptiveText

@Composable
fun TextAdvancedKeyboardKeyView(
    keyboardKey: TextAdvancedKeyboardKey,
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    KeyboardKeyView(
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(id = R.dimen.padding_small)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            keyboardKey.textSecondary?.let {
                AdaptiveText(
                    text = it,
                    percent = 0.8f,
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    textAlign = TextAlign.Start
                )
            } ?: run {
                Spacer(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }

            AdaptiveText(
                text = keyboardKey.text,
                percent = 0.8f,
                modifier = Modifier.fillMaxWidth().weight(1f),
                textAlign = TextAlign.Center
            )

            keyboardKey.textTertiary?.let {
                AdaptiveText(
                    text = it,
                    percent = 0.8f,
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    textAlign = TextAlign.End
                )
            } ?: run {
                Spacer(
                    modifier = Modifier.fillMaxWidth().weight(1f)
                )
            }
        }
    }
}

@Composable
fun IconAdvancedKeyboardKeyView(
    keyboardKey: IconAdvancedKeyboardKey,
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    KeyboardKeyView(
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
    ) {
        Icon(
            imageVector = keyboardKey.icon,
            contentDescription = keyboardKey.contentDescription,
            modifier = Modifier.fillMaxSize(0.35f)
        )
    }
}

@Composable
fun TextAdvancedKeyboardModifierKeyView(
    keyboardKey: TextAdvancedKeyboardModifierKey,
    touchDown: () -> Unit,
    touchUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    KeyboardKeyView(
        touchDown = touchDown,
        touchUp = touchUp,
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_standard),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            contentAlignment = Alignment.Center
        ) {
            AdaptiveText(
                text = keyboardKey.text,
                percent = 0.8f,
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.333f),
                textAlign = keyboardKey.textAlign
            )
        }
    }
}