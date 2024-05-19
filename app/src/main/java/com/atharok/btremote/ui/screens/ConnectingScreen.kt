package com.atharok.btremote.ui.screens

import android.bluetooth.BluetoothHidDevice
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.atharok.btremote.R
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.ui.components.AppScaffold
import com.atharok.btremote.ui.components.buttons.MaterialButton

@Composable
fun ConnectingScreen(
    deviceName: String,
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    openRemoteScreen: () -> Unit,
    cancelConnection: () -> Unit,
    modifier: Modifier = Modifier
) {
    StatefulConnectingScreen(
        bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
        navigateUp = navigateUp,
        openRemoteScreen = openRemoteScreen,
        cancelConnection = cancelConnection
    ) {
        StatelessConnectingScreen(
            deviceName = deviceName,
            cancelConnection = cancelConnection,
            modifier = modifier
        )
    }
}

@Composable
private fun StatefulConnectingScreen(
    bluetoothDeviceHidConnectionState: DeviceHidConnectionState,
    navigateUp: () -> Unit,
    openRemoteScreen: () -> Unit,
    cancelConnection: () -> Unit,
    content: @Composable () -> Unit
) {
    DisposableEffect(bluetoothDeviceHidConnectionState.state) {
        when(bluetoothDeviceHidConnectionState.state) {
            BluetoothHidDevice.STATE_CONNECTED -> openRemoteScreen()
            BluetoothHidDevice.STATE_DISCONNECTED -> navigateUp()
        }
        onDispose {}
    }

    BackHandler(enabled = true, onBack = cancelConnection)

    content()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun StatelessConnectingScreen(
    deviceName: String,
    cancelConnection: () -> Unit,
    modifier: Modifier = Modifier
) {
    AppScaffold(
        title = stringResource(id = R.string.connection),
        modifier = modifier,
        scrollBehavior = null
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_large),
                    vertical = dimensionResource(id = R.dimen.padding_small)
                ),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier)

            LoadingScreen(
                message = stringResource(id = R.string.bluetooth_device_connecting_message, deviceName),
                modifier = Modifier.padding( // Permet de centrer verticalement, indépendamment du inner Padding.
                    top = innerPadding.calculateBottomPadding() / 2f,
                    bottom = innerPadding.calculateTopPadding() / 2f
                )
            )

            MaterialButton(
                onClick = cancelConnection,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = android.R.string.cancel),
                icon = Icons.Rounded.Cancel,
            )
        }
    }
}