package com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import com.atharok.btremote.ui.views.keyboard.IconAdvancedKeyboardKeyView
import com.atharok.btremote.ui.views.keyboard.TextAdvancedKeyboardKeyView
import com.atharok.btremote.ui.views.keyboard.TextAdvancedKeyboardModifierKeyView

sealed class AdvancedKeyboardKey {
    abstract val byte: Byte
    abstract val weight: Float
    abstract val keyView: @Composable (
        touchDown: (Byte) -> Unit,
        touchUp: (Byte) -> Unit,
        Modifier
    ) -> Unit
}

data class TextAdvancedKeyboardKey(
    override val byte: Byte,
    override val weight: Float,
    val text: String,
    val textSecondary: String? = null,
    val textTertiary: String? = null
): AdvancedKeyboardKey() {
    override val keyView: @Composable (
        (Byte) -> Unit,
        (Byte) -> Unit,
        Modifier
    ) -> Unit = { touchDown, touchUp, modifier ->
        TextAdvancedKeyboardKeyView(
            keyboardKey = this,
            touchDown = {
                touchDown(byte)
            },
            touchUp = {
                touchUp(byte)
            },
            modifier = modifier
        )
    }
}

data class IconAdvancedKeyboardKey(
    override val byte: Byte,
    override val weight: Float,
    val icon: ImageVector,
    val contentDescription: String? = null
): AdvancedKeyboardKey() {
    override val keyView: @Composable (
            (Byte) -> Unit,
            (Byte) -> Unit,
            Modifier
    ) -> Unit = { touchDown, touchUp, modifier ->
        IconAdvancedKeyboardKeyView(
            keyboardKey = this,
            touchDown = {
                touchDown(byte)
            },
            touchUp = {
                touchUp(byte)
            },
            modifier = modifier
        )
    }
}

data class TextAdvancedKeyboardModifierKey(
    override val byte: Byte,
    override val weight: Float,
    val text: String,
    val textAlign: TextAlign
): AdvancedKeyboardKey() {
    override val keyView: @Composable (
            (Byte) -> Unit,
            (Byte) -> Unit,
            Modifier
    ) -> Unit = { touchDown, touchUp, modifier ->
        TextAdvancedKeyboardModifierKeyView(
            keyboardKey = this,
            touchDown = {
                touchDown(byte)
            },
            touchUp = {
                touchUp(byte)
            },
            modifier = modifier
        )
    }
}