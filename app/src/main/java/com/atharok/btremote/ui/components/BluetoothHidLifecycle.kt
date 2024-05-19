package com.atharok.btremote.ui.components

import android.bluetooth.BluetoothAdapter
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import kotlinx.coroutines.flow.StateFlow

@Composable
fun BluetoothHidLifecycle(
    isEnabled: Boolean,
    isBluetoothHidProfileConnectedFlow: StateFlow<Boolean>,
    hasBluetoothHidProfileConnectionFailedFlow: StateFlow<Boolean>,
    bluetoothDeviceHidConnectionStateFlow: StateFlow<DeviceHidConnectionState>,
    content: @Composable (
        isBluetoothEnabled: Boolean,
        isBluetoothHidProfileConnected: Boolean,
        hasBluetoothHidProfileConnectionFailed: Boolean,
        bluetoothDeviceHidConnectionState: DeviceHidConnectionState
    ) -> Unit
) {
    var isBluetoothEnabled: Boolean by remember { mutableStateOf(isEnabled) }
    CheckBluetoothActivation(
        isBluetoothEnabled = isBluetoothEnabled,
        onBluetoothEnabledChanged = { isBluetoothEnabled = it }
    )

    val isBluetoothHidProfileConnected by isBluetoothHidProfileConnectedFlow.collectAsStateWithLifecycle()
    val hasBluetoothHidProfileConnectionFailed by hasBluetoothHidProfileConnectionFailedFlow.collectAsStateWithLifecycle()
    val bluetoothDeviceHidConnectedState by bluetoothDeviceHidConnectionStateFlow.collectAsStateWithLifecycle()

    content(isBluetoothEnabled, isBluetoothHidProfileConnected, hasBluetoothHidProfileConnectionFailed, bluetoothDeviceHidConnectedState)
}

@Composable
private fun CheckBluetoothActivation(
    isBluetoothEnabled: Boolean,
    onBluetoothEnabledChanged: (Boolean) -> Unit
) {
    SystemBroadcastReceiver(systemAction = BluetoothAdapter.ACTION_STATE_CHANGED) { intent ->
        if(intent?.action == BluetoothAdapter.ACTION_STATE_CHANGED) {
            val state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
            (state == BluetoothAdapter.STATE_ON).let {
                if(isBluetoothEnabled != it)
                    onBluetoothEnabledChanged(it)
            }
        }
    }
}