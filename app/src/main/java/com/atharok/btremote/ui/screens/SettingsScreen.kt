package com.atharok.btremote.ui.screens

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.rounded.Keyboard
import androidx.compose.material.icons.rounded.Mouse
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.MOUSE_SPEED_DEFAULT_VALUE
import com.atharok.btremote.common.utils.SOURCE_CODE_LINK
import com.atharok.btremote.common.utils.WEB_SITE_LINK
import com.atharok.btremote.common.utils.isDynamicColorsAvailable
import com.atharok.btremote.domain.entity.ThemeEntity
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.ListDialog
import com.atharok.btremote.ui.components.NavigateUpAction
import com.atharok.btremote.ui.components.TextNormal
import com.atharok.btremote.ui.components.TextNormalSecondary
import kotlinx.coroutines.flow.Flow

@Composable
fun SettingsScreen(
    navigateUp: () -> Unit,
    openThirdLibrariesScreen: () -> Unit,
    settingsViewModel: SettingsViewModel,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.settings),
        modifier = modifier,
        navigateUp = {
            NavigateUpAction(navigateUp)
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
        ) {

            val context = LocalContext.current
            val uriHandler = LocalUriHandler.current
            val horizontalPadding = dimensionResource(id = R.dimen.padding_large)
            val verticalPadding = dimensionResource(id = R.dimen.padding_medium)

            // ---- Appearance ----

            TitleItem(
                text = stringResource(id = R.string.appearance),
                icon = Icons.Rounded.Palette,
                iconDescription = stringResource(id = R.string.appearance),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            ThemeItem(
                themeFlow = settingsViewModel.theme,
                onThemeChange = { settingsViewModel.changeTheme(it) },
                context = context,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            StatefulSettingsSwitchItem(
                isEnabledFlow = settingsViewModel.useBlackColorForDarkTheme,
                onEnabledFlowChange = { settingsViewModel.setUseBlackColorForDarkTheme(it) },
                initialValue = false,
                primaryText = stringResource(id = R.string.theme_black),
                secondaryText = stringResource(id = R.string.theme_black_oled_info),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            if(isDynamicColorsAvailable()) {
                StatefulSettingsSwitchItem(
                    isEnabledFlow = settingsViewModel.useDynamicColors,
                    onEnabledFlowChange = { settingsViewModel.setUseDynamicColors(it) },
                    initialValue = true,
                    primaryText = stringResource(id = R.string.dynamic_colors),
                    secondaryText = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        )
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- Mouse ----

            TitleItem(
                text = stringResource(id = R.string.mouse),
                icon = Icons.Rounded.Mouse,
                iconDescription = stringResource(id = R.string.mouse),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            MouseSpeedItem(
                mouseSpeedFlow = settingsViewModel.mouseSpeed,
                onMouseSpeedChange = { settingsViewModel.saveMouseSpeed(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            StatefulSettingsSwitchItem(
                isEnabledFlow = settingsViewModel.shouldInvertMouseScrollingDirection,
                onEnabledFlowChange = { settingsViewModel.saveInvertMouseScrollingDirection(it) },
                initialValue = false,
                primaryText = stringResource(id = R.string.invert_mouse_scrolling_direction),
                secondaryText = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            StatefulSettingsSwitchItem(
                isEnabledFlow = settingsViewModel.useGyroscope,
                onEnabledFlowChange = { settingsViewModel.saveUseGyroscope(it) },
                initialValue = false,
                primaryText = stringResource(id = R.string.use_the_gyroscope_to_control_the_mouse),
                secondaryText = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )
            
            // ---- Keyboard and Input Field ----

            TitleItem(
                text = stringResource(id = R.string.keyboard_and_input_field),
                icon = Icons.Rounded.Keyboard,
                iconDescription = stringResource(id = R.string.keyboard),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            KeyboardLanguageItem(
                languageFlow = settingsViewModel.keyboardLanguage,
                onLanguageChange = { settingsViewModel.changeKeyboardLanguage(it) },
                context = context,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            StatefulSettingsSwitchItem(
                isEnabledFlow = settingsViewModel.mustClearInputField,
                onEnabledFlowChange = { settingsViewModel.saveMustClearInputField(it) },
                initialValue = true,
                primaryText = stringResource(id = R.string.clear_input_field),
                secondaryText = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            StatefulSettingsSwitchItem(
                isEnabledFlow = settingsViewModel.useAdvancedKeyboard,
                onEnabledFlowChange = { settingsViewModel.saveUseAdvancedKeyboard(it) },
                initialValue = false,
                primaryText = stringResource(id = R.string.advanced_keyboard),
                secondaryText = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = verticalPadding)
            )

            // ---- About ----

            TitleItem(
                text = stringResource(id = R.string.about),
                icon = Icons.Outlined.Info,
                iconDescription = stringResource(id = R.string.about),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                val activity = LocalContext.current as Activity
                TextItem(
                    text = stringResource(id = R.string.language),
                    modifier = Modifier
                        .clickable {
                            activity.startActivity(
                                Intent(
                                    Settings.ACTION_APP_LOCALE_SETTINGS,
                                    Uri.fromParts("package", activity.packageName, null)
                                )
                            )
                        }
                        .fillMaxWidth()
                        .padding(
                            horizontal = horizontalPadding,
                            vertical = verticalPadding
                        )
                )
            }

            TextItem(
                text = stringResource(id = R.string.third_party_library),
                modifier = Modifier
                    .clickable {
                        openThirdLibrariesScreen()
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            TextItem(
                text = stringResource(id = R.string.website),
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(WEB_SITE_LINK)
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )

            TextItem(
                text = stringResource(id = R.string.source_code),
                modifier = Modifier
                    .clickable {
                        uriHandler.openUri(SOURCE_CODE_LINK)
                    }
                    .fillMaxWidth()
                    .padding(
                        horizontal = horizontalPadding,
                        vertical = verticalPadding
                    )
            )
        }
    }
}

@Composable
private fun ThemeItem(
    themeFlow: Flow<ThemeEntity>,
    onThemeChange: (ThemeEntity) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    val theme: ThemeEntity by themeFlow
        .collectAsStateWithLifecycle(initialValue = ThemeEntity.SYSTEM)

    var isShowingDialog by remember { mutableStateOf(false) }

    SettingsListDialogItem(
        value = theme,
        onValueChange = onThemeChange,
        items = ThemeEntity.entries,
        convertValueToString = { context.getString(it.stringRes) },
        showDialog = isShowingDialog,
        onShowDialogChange = { isShowingDialog = it },
        title = R.string.theme,
        dialogMessage = null,
        modifier = modifier
    )
}

@Composable
private fun MouseSpeedItem(
    mouseSpeedFlow: Flow<Float>,
    onMouseSpeedChange: (Float) -> Unit,
    modifier: Modifier = Modifier
) {
    val mouseSpeed by mouseSpeedFlow
        .collectAsStateWithLifecycle(initialValue = MOUSE_SPEED_DEFAULT_VALUE)

    Column(
        modifier = modifier
    ) {
        TextNormal(
            text = stringResource(id = R.string.mouse_pointer_speed) + " (x$mouseSpeed)",
            modifier = Modifier.fillMaxWidth().padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        Slider(
            value = mouseSpeed,
            onValueChange = onMouseSpeedChange,
            valueRange = 1f..5f,
            steps = 15,
        )
    }
}

@Composable
private fun KeyboardLanguageItem(
    languageFlow: Flow<KeyboardLanguage>,
    onLanguageChange: (KeyboardLanguage) -> Unit,
    context: Context,
    modifier: Modifier = Modifier
) {
    val language: KeyboardLanguage by languageFlow
        .collectAsStateWithLifecycle(initialValue = KeyboardLanguage.ENGLISH_US)

    var isShowingDialog by remember { mutableStateOf(false) }

    SettingsListDialogItem(
        value = language,
        onValueChange = onLanguageChange,
        items = KeyboardLanguage.entries.sortedBy { context.getString(it.language) },
        convertValueToString = { context.getString(it.language) },
        showDialog = isShowingDialog,
        onShowDialogChange = { isShowingDialog = it },
        title = R.string.keyboard_language,
        dialogMessage = stringResource(id = R.string.keyboard_language_info),
        modifier = modifier
    )
}

// --- Reusable components ----

@Composable
fun <T> SettingsListDialogItem(
    value: T,
    onValueChange: (T) -> Unit,
    items: List<T>,
    convertValueToString: (T) -> String,
    showDialog: Boolean,
    onShowDialogChange: (Boolean) -> Unit,
    @StringRes title: Int,
    dialogMessage: String?,
    modifier: Modifier = Modifier
) {
    if(showDialog) {
        ListDialog(
            confirmButtonText = stringResource(android.R.string.ok),
            dismissButtonText = stringResource(android.R.string.cancel),
            onConfirmation = { index ->
                onValueChange(items[index])
                onShowDialogChange(false)
            },
            onDismissRequest = { onShowDialogChange(false) },
            dialogTitle = stringResource(title),
            dialogMessage = dialogMessage,
            items = items.map { convertValueToString(it) },
            defaultItemIndex = items.indexOf(value)
        )
    }

    Column(
        modifier = Modifier
            .clickable { onShowDialogChange(true) }
            .then(modifier)
    ) {
        TextNormal(text = stringResource(id = title))
        TextNormalSecondary(text = convertValueToString(value))
    }
}

@Composable
private fun StatefulSettingsSwitchItem(
    isEnabledFlow: Flow<Boolean>,
    onEnabledFlowChange: (Boolean) -> Unit,
    initialValue: Boolean,
    primaryText: String,
    secondaryText: String?,
    modifier: Modifier = Modifier
) {
    val isEnabled: Boolean by isEnabledFlow.collectAsStateWithLifecycle(initialValue = initialValue)

    StatelessSettingsSwitchItem(
        primaryText = primaryText,
        secondaryText = secondaryText,
        checked = isEnabled,
        onCheckedChange = onEnabledFlowChange,
        modifier = modifier
    )
}

@Composable
private fun StatelessSettingsSwitchItem(
    primaryText: String,
    secondaryText: String?,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .clickable { onCheckedChange(!checked) }
            .then(modifier),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(0.8f)
        ) {
            TextNormal(text = primaryText)
            secondaryText?.let {
                TextNormalSecondary(text = it)
            }
        }

        Switch(
            checked = checked,
            onCheckedChange = null//onCheckedChange
        )
    }
}

@Composable
private fun TitleItem(
    text: String,
    icon: ImageVector,
    iconDescription: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            imageVector = icon,
            contentDescription = iconDescription,
            modifier = Modifier.size(dimensionResource(id = R.dimen.medium_icon_size)),
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.secondary)
        )
        TextNormal(
            text = text,
            modifier = Modifier.padding(start = dimensionResource(id = R.dimen.padding_standard)),
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Composable
private fun TextItem(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        TextNormal(
            text = text,
            modifier = Modifier.fillMaxSize()
        )
    }
}