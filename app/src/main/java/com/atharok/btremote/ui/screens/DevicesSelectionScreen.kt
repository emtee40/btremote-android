package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothHidDevice
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.HelpAction
import com.atharok.btremote.ui.components.LoadingDialog
import com.atharok.btremote.ui.components.PairingNewDeviceAction
import com.atharok.btremote.ui.components.SettingsAction
import com.atharok.btremote.ui.components.SimpleDialog
import com.atharok.btremote.ui.components.TextNormalSecondary
import com.atharok.btremote.ui.views.DeviceItemView
import com.atharok.btremote.ui.views.DevicesSelectionScreenHelpModalBottomSheet
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DevicesSelectionScreen(
    isBluetoothEnabled: Boolean,
    isBluetoothServiceStarted: Boolean,
    isBluetoothHidProfileRegistered: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    closeApp: () -> Unit,
    navigateUp: () -> Unit,
    startHidService: () -> Unit,
    stopHidService: () -> Unit,
    devicesFlow: StateFlow<List<DeviceEntity>>,
    findBondedDevices: () -> Unit,
    connectDevice: (DeviceEntity) -> Unit,
    disconnectDevice: () -> Unit,
    openRemoteScreen: (deviceName: String) -> Unit,
    openBluetoothScanningDeviceScreen: () -> Unit,
    openSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulDevicesSelectionScreen(
        devicesFlow = devicesFlow,
        findBondedDevices = findBondedDevices,
        isBluetoothEnabled = isBluetoothEnabled,
        isBluetoothServiceStarted = isBluetoothServiceStarted,
        bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
        closeApp = closeApp,
        navigateUp = navigateUp,
        startHidService = startHidService,
        stopHidService = stopHidService,
        openRemoteScreen = openRemoteScreen,
    ) { devices ->

        var showHelpBottomSheet: Boolean by remember { mutableStateOf(false) }
        StatelessDevicesSelectionScreen(
            devices = devices,
            connectDevice = connectDevice,
            openBluetoothScanningDeviceScreen = openBluetoothScanningDeviceScreen,
            openSettings = openSettings,
            showHelpBottomSheet = showHelpBottomSheet,
            onShowHelpBottomSheetChanged = { showHelpBottomSheet = it },
            modifier = modifier
        )

        if(isBluetoothServiceStarted && !isBluetoothHidProfileRegistered) {
            SimpleDialog(
                confirmButtonText = stringResource(id = R.string.retry),
                dismissButtonText = stringResource(id = R.string.close),
                onConfirmation = {
                    stopHidService()
                    startHidService()
                },
                onDismissRequest = closeApp,
                dialogTitle = stringResource(id = R.string.error),
                dialogText = stringResource(id = R.string.bluetooth_failed_to_register_app_message)
            )
        } else if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_CONNECTING) {
            LoadingDialog(
                title = stringResource(id = R.string.connection),
                message = stringResource(id = R.string.bluetooth_device_connecting_message, bluetoothDeviceHidConnectionState.deviceName),
                buttonText = stringResource(id = android.R.string.cancel),
                onButtonClick = disconnectDevice
            )
        }
    }
}

@Composable
private fun StatefulDevicesSelectionScreen(
    devicesFlow: StateFlow<List<DeviceEntity>>,
    findBondedDevices: () -> Unit,
    isBluetoothEnabled: Boolean,
    isBluetoothServiceStarted: Boolean,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    closeApp: () -> Unit,
    navigateUp: () -> Unit,
    startHidService: () -> Unit,
    stopHidService: () -> Unit,
    openRemoteScreen: (deviceName: String) -> Unit,
    content: @Composable (devices: List<DeviceEntity>) -> Unit
) {
    DisposableEffect(isBluetoothEnabled) {
        if(!isBluetoothEnabled) {
            stopHidService()
            navigateUp()
        }
        onDispose {}
    }

    DisposableEffect(isBluetoothServiceStarted) {
        if(!isBluetoothServiceStarted && isBluetoothEnabled) {
            startHidService()
        }
        onDispose {}
    }

    DisposableEffect(bluetoothDeviceHidConnectionState.state) {
        if(bluetoothDeviceHidConnectionState.state == BluetoothHidDevice.STATE_CONNECTED) {
            openRemoteScreen(bluetoothDeviceHidConnectionState.deviceName)
        }
        onDispose {}
    }

    BackHandler(enabled = true, onBack = closeApp)

    val devices by devicesFlow.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        findBondedDevices()
    }

    content(devices)
}

@Composable
private fun StatelessDevicesSelectionScreen(
    devices: List<DeviceEntity>,
    connectDevice: (DeviceEntity) -> Unit,
    openBluetoothScanningDeviceScreen: () -> Unit,
    openSettings: () -> Unit,
    showHelpBottomSheet: Boolean,
    onShowHelpBottomSheetChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.devices),
        modifier = modifier,
        topBarActions = {
            PairingNewDeviceAction(openBluetoothScanningDeviceScreen)
            HelpAction(showHelp = { onShowHelpBottomSheetChanged(!showHelpBottomSheet) })
            SettingsAction(openSettings)
        }
    ) { innerPadding ->

        DevicesListView(
            devices = devices,
            onItemClick = connectDevice,
            modifier = Modifier,
            contentPadding = innerPadding
        )

        HelpBottomSheet(
            showHelpBottomSheet = showHelpBottomSheet,
            onShowHelpBottomSheetChanged = onShowHelpBottomSheetChanged
        )
    }
}

@Composable
private fun DevicesListView(
    devices: List<DeviceEntity>,
    onItemClick: (DeviceEntity) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding
    ) {
        item {
            TextNormalSecondary(
                text = stringResource(id = R.string.paired_devices),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_standard))
                    .padding(
                        start = dimensionResource(id = R.dimen.padding_small),
                        top = dimensionResource(id = R.dimen.padding_small)
                    )
            )
        }
        items(devices) { device ->
            DeviceItemView(
                name = device.name,
                macAddress = device.macAddress,
                icon = device.imageVector,
                onClick = { onItemClick(device) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = dimensionResource(id = R.dimen.padding_standard),
                        vertical = dimensionResource(id = R.dimen.padding_small)
                    )
            )
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
        DevicesSelectionScreenHelpModalBottomSheet(
            onDismissRequest = { onShowHelpBottomSheetChanged(false) },
            modifier = modifier
        )
    }
}