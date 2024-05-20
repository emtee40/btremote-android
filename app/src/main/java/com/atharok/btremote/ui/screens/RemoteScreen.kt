package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothHidDevice
import android.content.res.Configuration
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.MOUSE_SPEED_DEFAULT_VALUE
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.MouseInput
import com.atharok.btremote.domain.entity.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.DialPadLayout
import com.atharok.btremote.ui.components.DirectionButtonsAction
import com.atharok.btremote.ui.components.DisconnectDropdownMenuItem
import com.atharok.btremote.ui.components.FadeAnimatedContent
import com.atharok.btremote.ui.components.HelpDropdownMenuItem
import com.atharok.btremote.ui.components.KeyboardOverflowMenu
import com.atharok.btremote.ui.components.KeyboardView
import com.atharok.btremote.ui.components.LoadingDialog
import com.atharok.btremote.ui.components.MoreOverflowMenu
import com.atharok.btremote.ui.components.MouseAction
import com.atharok.btremote.ui.components.MousePadLayout
import com.atharok.btremote.ui.components.RemoteScreenHelpModalBottomSheet
import com.atharok.btremote.ui.components.SettingsDropdownMenuItem
import com.atharok.btremote.ui.components.buttons.BackRemoteButton
import com.atharok.btremote.ui.components.buttons.DirectionalButtons
import com.atharok.btremote.ui.components.buttons.HomeRemoteButton
import com.atharok.btremote.ui.components.buttons.MultimediaButtons
import com.atharok.btremote.ui.components.buttons.MuteRemoteButton
import com.atharok.btremote.ui.components.buttons.VolumeVerticalRemoteButtons
import kotlinx.coroutines.flow.Flow

private enum class NavigationView {
    DIRECTION,
    MOUSE
}

@Composable
fun RemoteScreen(
    deviceName: String,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    closeApp: () -> Unit,
    openSettings: () -> Unit,
    disconnectDevice: () -> Unit,
    sendRemoteKeyReport: (ByteArray) -> Unit,
    sendMouseKeyReport: (MouseInput, Float, Float, Float) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String, KeyboardLayout) -> Unit,
    keyboardLanguageFlow: Flow<KeyboardLanguage>,
    getKeyboardLayout: (KeyboardLanguage) -> KeyboardLayout,
    mustClearInputFieldFlow: Flow<Boolean>,
    mouseSpeedFlow: Flow<Float>,
    shouldInvertMouseScrollingDirectionFlow: Flow<Boolean>,
    modifier: Modifier = Modifier
) {
    StatefulRemoteScreen(
        bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
        navigateUp = navigateUp,
        closeApp = closeApp,
        keyboardLanguageFlow = keyboardLanguageFlow,
        getKeyboardLayout = getKeyboardLayout,
        mustClearInputFieldFlow = mustClearInputFieldFlow,
        mouseSpeedFlow = mouseSpeedFlow,
        shouldInvertMouseScrollingDirectionFlow = shouldInvertMouseScrollingDirectionFlow
    ) { keyboardLayout: KeyboardLayout, mustClearInputField: Boolean, mouseSpeed: Float, shouldInvertMouseScrollingDirection: Boolean ->

        var navigationView by rememberSaveable {
            mutableStateOf(NavigationView.DIRECTION)
        }

        var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }

        StatelessRemoteScreen(
            deviceName = deviceName,
            openSettings = openSettings,
            disconnectDevice = disconnectDevice,
            navigationView = navigationView,
            onNavigationViewChanged = { navigationView = it },
            showHelpBottomSheet = showHelpBottomSheet,
            onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
            sendRemoteKeyReport = { bytes -> sendRemoteKeyReport(bytes) },
            sendMouseKeyReport = { input: MouseInput, x: Float, y: Float, wheel: Float -> sendMouseKeyReport(input, x, y, wheel) },
            sendKeyboardKeyReport = { bytes -> sendKeyboardKeyReport(bytes) },
            sendTextReport = { text -> sendTextReport(text, keyboardLayout) },
            keyboardLayout = keyboardLayout,
            mustClearInputField = mustClearInputField,
            mouseSpeed = mouseSpeed,
            shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
            modifier = modifier
        )

        if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_DISCONNECTING) {
            LoadingDialog(
                title = stringResource(id = R.string.connection),
                message = stringResource(id = R.string.bluetooth_device_disconnecting_message, bluetoothDeviceHidConnectionState.deviceName),
                buttonText = stringResource(id = R.string.disconnect),
                onButtonClick = disconnectDevice
            )
        }
    }
}

@Composable
private fun StatefulRemoteScreen(
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    closeApp: () -> Unit,
    keyboardLanguageFlow: Flow<KeyboardLanguage>,
    getKeyboardLayout: (KeyboardLanguage) -> KeyboardLayout,
    mustClearInputFieldFlow: Flow<Boolean>,
    mouseSpeedFlow: Flow<Float>,
    shouldInvertMouseScrollingDirectionFlow: Flow<Boolean>,
    content: @Composable (
        keyboardLayout: KeyboardLayout,
        mustClearInputField: Boolean,
        mouseSpeed: Float,
        shouldInvertMouseScrollingDirection: Boolean
    ) -> Unit
) {

    BackHandler(enabled = true, onBack = closeApp)

    DisposableEffect(bluetoothDeviceHidConnectionState.state) {
        if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_DISCONNECTED) {
            navigateUp()
        }
        onDispose {}
    }

    val keyboardLanguage: KeyboardLanguage by keyboardLanguageFlow
        .collectAsStateWithLifecycle(initialValue = KeyboardLanguage.ENGLISH_US)

    val mustClearInputField: Boolean by mustClearInputFieldFlow
        .collectAsStateWithLifecycle(initialValue = false)

    val mouseSpeed: Float by mouseSpeedFlow
        .collectAsStateWithLifecycle(initialValue = MOUSE_SPEED_DEFAULT_VALUE)

    val shouldInvertMouseScrollingDirection: Boolean by shouldInvertMouseScrollingDirectionFlow
        .collectAsStateWithLifecycle(initialValue = false)

    content(
        getKeyboardLayout(keyboardLanguage),
        mustClearInputField,
        mouseSpeed,
        shouldInvertMouseScrollingDirection
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatelessRemoteScreen(
    deviceName: String,
    openSettings: () -> Unit,
    disconnectDevice: () -> Unit,
    navigationView: NavigationView,
    onNavigationViewChanged: (NavigationView) -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    sendRemoteKeyReport: (ByteArray) -> Unit,
    sendMouseKeyReport: (MouseInput, Float, Float, Float) -> Unit,
    sendKeyboardKeyReport: (ByteArray) -> Unit,
    sendTextReport: (String) -> Unit,
    keyboardLayout: KeyboardLayout,
    mustClearInputField: Boolean,
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    modifier: Modifier = Modifier
) {

    AppScaffold(
        title = deviceName,
        modifier = modifier,
        scrollBehavior = null,
        topBarActions = {

            FadeAnimatedContent(targetState = navigationView) {
                when (it) {
                    NavigationView.MOUSE -> {
                        DirectionButtonsAction(
                            showDirectionButtons = {
                                onNavigationViewChanged(NavigationView.DIRECTION)
                            }
                        )
                    }

                    NavigationView.DIRECTION -> {
                        MouseAction(
                            showMousePad = {
                                onNavigationViewChanged(NavigationView.MOUSE)
                            }
                        )
                    }
                }
            }

            KeyboardOverflowMenu {
                KeyboardView(
                    mustClearInputField = mustClearInputField,
                    sendKeyboardKeyReport = sendKeyboardKeyReport,
                    sendTextReport = sendTextReport,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            MoreOverflowMenu { closeDropdownMenu: () -> Unit ->
                SettingsDropdownMenuItem(
                    showSettingsScreen = {
                        closeDropdownMenu()
                        openSettings()
                    }
                )
                HelpDropdownMenuItem(
                    showHelp = {
                        closeDropdownMenu()
                        onShowHelpBottomSheetChanged(!showHelpBottomSheet)
                    }
                )
                DisconnectDropdownMenuItem(
                    disconnect = {
                        closeDropdownMenu()
                        disconnectDevice()
                    }
                )
            }
        }
    ) { innerPadding ->
        RemoteView(
            sendRemoteKeyReport = sendRemoteKeyReport,
            sendMouseKeyReport = sendMouseKeyReport,
            sendNumberKeyReport = sendKeyboardKeyReport,
            keyboardLayout = keyboardLayout,
            navigationView = navigationView,
            mouseSpeed = mouseSpeed,
            shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
            innerPadding = innerPadding
        )

        HelpBottomSheet(
            showHelpBottomSheet = showHelpBottomSheet,
            onShowHelpBottomSheetChanged = onShowHelpBottomSheetChanged
        )
    }
}

@Composable
private fun RemoteView(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendMouseKeyReport: (input: MouseInput, x: Float, y: Float, wheel: Float) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    keyboardLayout: KeyboardLayout,
    navigationView: NavigationView,
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    innerPadding: PaddingValues
) {
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if(isLandscape) {
        RemoteLandscapeView(
            sendRemoteKeyReport = sendRemoteKeyReport,
            sendMouseKeyReport = sendMouseKeyReport,
            sendNumberKeyReport = sendNumberKeyReport,
            keyboardLayout = keyboardLayout,
            navigationView = navigationView,
            mouseSpeed = mouseSpeed,
            shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    } else {
        RemotePortraitView(
            sendRemoteKeyReport = sendRemoteKeyReport,
            sendMouseKeyReport = sendMouseKeyReport,
            sendNumberKeyReport = sendNumberKeyReport,
            keyboardLayout = keyboardLayout,
            navigationView = navigationView,
            mouseSpeed = mouseSpeed,
            shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
private fun RemoteLandscapeView(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendMouseKeyReport: (input: MouseInput, x: Float, y: Float, wheel: Float) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    keyboardLayout: KeyboardLayout,
    navigationView: NavigationView,
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    modifier: Modifier = Modifier
) {
    var rowSize by remember { mutableStateOf(Size.Zero) }

    Row(
        modifier = modifier
            .onGloballyPositioned { layoutCoordinates -> rowSize = layoutCoordinates.size.toSize() },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        NavigationBox(
            sendRemoteKeyReport = sendRemoteKeyReport,
            sendMouseKeyReport = sendMouseKeyReport,
            navigationView = navigationView,
            mouseSpeed = mouseSpeed,
            shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
            modifier = Modifier
                .widthIn(max = with(LocalDensity.current) { (0.5f * rowSize.width).toDp() })
                .align(Alignment.CenterVertically),
        )

        RemoteLayout(
            sendRemoteKeyReport = sendRemoteKeyReport,
            sendNumberKeyReport = sendNumberKeyReport,
            keyboardLayout = keyboardLayout,
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Composable
private fun RemotePortraitView(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendMouseKeyReport: (input: MouseInput, x: Float, y: Float, wheel: Float) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    keyboardLayout: KeyboardLayout,
    navigationView: NavigationView,
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        RemoteLayout(
            sendRemoteKeyReport = sendRemoteKeyReport,
            sendNumberKeyReport = sendNumberKeyReport,
            keyboardLayout = keyboardLayout,
            modifier = Modifier
                .heightIn(
                    max = with(LocalConfiguration.current) {
                        if (screenHeightDp >= screenWidthDp * 1.9f) // Si la hauteur de l'appareil est suffisamment haute par rapport à sa largeur (ratio ~ 1/2)
                            screenWidthDp.dp // On peut se permettre de prendre pour hauteur la largeur de l'écran
                        else // Sinon
                            (screenHeightDp * 0.50).dp // On prend 45% de la hauteur de l'écran
                    }
                )
                .align(Alignment.CenterHorizontally),
        )

        NavigationBox(
            sendRemoteKeyReport = sendRemoteKeyReport,
            sendMouseKeyReport = sendMouseKeyReport,
            navigationView = navigationView,
            mouseSpeed = mouseSpeed,
            shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )
    }
}

@Composable
fun RemoteLayout(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendNumberKeyReport: (bytes: ByteArray) -> Unit,
    keyboardLayout: KeyboardLayout,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
    ) {
        MultimediaButtons(
            sendRemoteKey = sendRemoteKeyReport,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(dimensionResource(id = R.dimen.padding_standard))
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(3f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // Volume
            Column(
                modifier = Modifier.weight(1f),
            ) {
                VolumeVerticalRemoteButtons(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(2f)
                        .padding(dimensionResource(id = R.dimen.padding_standard))
                        .align(Alignment.Start)
                )

                MuteRemoteButton(
                    sendReport = sendRemoteKeyReport,
                    modifier = Modifier
                        .weight(1f)
                        .padding(dimensionResource(id = R.dimen.padding_standard))
                )
            }

            // Dial Pad
            DialPadLayout(
                sendRemoteKeyReport = sendRemoteKeyReport,
                sendNumberKeyReport = sendNumberKeyReport,
                keyboardLayout = keyboardLayout,
                modifier = Modifier.weight(4f)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            BackRemoteButton(
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.padding_standard))
            )

            HomeRemoteButton(
                sendReport = sendRemoteKeyReport,
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.padding_standard))
            )
        }
    }
}

@Composable
private fun NavigationBox(
    sendRemoteKeyReport: (bytes: ByteArray) -> Unit,
    sendMouseKeyReport: (input: MouseInput, x: Float, y: Float, wheel: Float) -> Unit,
    navigationView: NavigationView,
    mouseSpeed: Float,
    shouldInvertMouseScrollingDirection: Boolean,
    modifier: Modifier = Modifier,
    contentAlignment: Alignment = Alignment.Center
) {
    Box(
        modifier = modifier,
        contentAlignment = contentAlignment
    ) {
        FadeAnimatedContent(targetState = navigationView) {
            when(it) {
                NavigationView.DIRECTION -> {
                    DirectionalButtons(
                        sendRemoteKeyReport = sendRemoteKeyReport,
                        modifier = Modifier
                            .aspectRatio(1f)
                            .padding(dimensionResource(id = R.dimen.padding_standard))
                    )
                }

                NavigationView.MOUSE -> {
                    MousePadLayout(
                        mouseSpeed = mouseSpeed,
                        shouldInvertMouseScrollingDirection = shouldInvertMouseScrollingDirection,
                        sendMouseInput = sendMouseKeyReport,
                        modifier = Modifier
                    )
                }
            }
        }
    }
}

@Composable
private fun HelpBottomSheet(
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    if(showHelpBottomSheet) {
        RemoteScreenHelpModalBottomSheet(
            onDismissRequest = { onShowHelpBottomSheetChanged(false) },
            modifier = modifier
        )
    }
}