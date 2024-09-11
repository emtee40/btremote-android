package com.atharok.btremote.ui.views.keyboard

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardModalBottomSheet(
    showKeyboardBottomSheet: Boolean,
    onShowKeyboardBottomSheetChanged: (Boolean) -> Unit,
    windowInsets: WindowInsets,
    modifier: Modifier = Modifier,
    content: @Composable (ColumnScope.() -> Unit)
) {
    if(showKeyboardBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onShowKeyboardBottomSheetChanged(false) },
            modifier = modifier,
            sheetState = rememberModalBottomSheetState(true),
            contentWindowInsets = {
                windowInsets
            }
        ) {
            content()
        }
    }
}