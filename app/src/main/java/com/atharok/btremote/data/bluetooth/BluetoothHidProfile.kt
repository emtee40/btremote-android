package com.atharok.btremote.data.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothHidDevice
import android.bluetooth.BluetoothHidDeviceAppSdpSettings
import android.bluetooth.BluetoothProfile
import android.bluetooth.BluetoothProfile.ServiceListener
import android.content.Context
import androidx.annotation.RequiresPermission
import com.atharok.btremote.common.utils.KEYBOARD_REPORT_ID
import com.atharok.btremote.common.utils.REMOTE_INPUT_NONE
import com.atharok.btremote.common.utils.checkBluetoothConnectPermission
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidProfile(
    private val context: Context,
    private val adapter: BluetoothAdapter?,
    private val hidSettings: BluetoothHidDeviceAppSdpSettings
) {

    private var bluetoothHidDevice: BluetoothHidDevice? = null

    private val _isBluetoothServiceStarted: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBluetoothServiceStarted: StateFlow<Boolean> = _isBluetoothServiceStarted

    private val _isBluetoothHidProfileRegistered: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBluetoothHidProfileRegistered: StateFlow<Boolean> = _isBluetoothHidProfileRegistered

    // ---- HID Profile ----

    fun startBluetoothHidProfile() {
        adapter?.getProfileProxy(context, serviceListener, BluetoothProfile.HID_DEVICE)
    }

    fun stopBluetoothHidProfile() {
        bluetoothHidDevice?.let { hidDevice ->
            if (checkBluetoothConnectPermission(context)) {
                disconnectDevice(hidDevice)
                hidDevice.unregisterApp()
            }
            adapter?.closeProfileProxy(BluetoothProfile.HID_DEVICE, hidDevice)
        }
        bluetoothHidDevice = null
        _deviceHidConnectionState.value = DeviceHidConnectionState()
        _isBluetoothServiceStarted.value = false
    }

    // ---- BluetoothProfile.ServiceListener implementation ----

    private val serviceListener = object : ServiceListener {
        override fun onServiceConnected(i: Int, bluetoothProfile: BluetoothProfile?) {
            if(i == BluetoothProfile.HID_DEVICE) {
                bluetoothHidDevice = bluetoothProfile as? BluetoothHidDevice?
                bluetoothHidDevice?.let { hidDevice ->
                    if (checkBluetoothConnectPermission(context)) {
                        _isBluetoothHidProfileRegistered.value = hidDevice.registerApp(hidSettings, null, null, Runnable::run, callback)
                    }
                }
            }
            _isBluetoothServiceStarted.value = true
        }

        override fun onServiceDisconnected(i: Int) {}
    }


    // ---- BluetoothHidDevice.Callback implementation ----

    private val callback = object : BluetoothHidDevice.Callback() {

        override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
            super.onAppStatusChanged(pluggedDevice, registered)
            _isBluetoothHidProfileRegistered.value = registered
        }

        override fun onConnectionStateChanged(device: BluetoothDevice?, state: Int) {
            super.onConnectionStateChanged(device, state)
            if (checkBluetoothConnectPermission(context)) {
                _deviceHidConnectionState.value = DeviceHidConnectionState(state, device?.name ?: "")
            }
        }
    }

    // ---- Device Connection ----

    private var bluetoothDevice: BluetoothDevice? = null

    fun getBluetoothDeviceName(): String? {
        return if (checkBluetoothConnectPermission(context)) {
            bluetoothDevice?.name
        } else null
    }

    fun connectDevice(deviceAddress: String): Boolean {
        if (checkBluetoothConnectPermission(context)) {
            bluetoothHidDevice?.let {
                return connectDevice(it, deviceAddress)
            }
        }
        return false
    }

    fun disconnectDevice(): Boolean {
        if (checkBluetoothConnectPermission(context)) {
            bluetoothHidDevice?.let {
                return disconnectDevice(it)
            }
        }
        return false
    }

    @RequiresPermission(value = "android.permission.BLUETOOTH_CONNECT")
    private fun connectDevice(hidDevice: BluetoothHidDevice, deviceAddress: String): Boolean {
        var isConnected = false
        bluetoothDevice = adapter?.getRemoteDevice(deviceAddress)
        bluetoothDevice?.let {
            isConnected = hidDevice.connect(it)
        }
        return isConnected
    }

    @RequiresPermission(value = "android.permission.BLUETOOTH_CONNECT")
    private fun disconnectDevice(hidDevice: BluetoothHidDevice): Boolean {
        var isDisconnected = false
        bluetoothDevice?.let {
            // disconnect() is called twice because, during my tests on a Pixel 3a, the state
            // changes to DISCONNECTING but never to DISCONNECTED if the call is made only once.
            // This error occurs only on this device and not on the others I tested. It seems to
            // be due to a bug in the library or the device.
            hidDevice.disconnect(it)
            isDisconnected = hidDevice.disconnect(it)
        }
        bluetoothDevice = null
        return isDisconnected
    }

    // ---- StateFlow ----

    private val _deviceHidConnectionState: MutableStateFlow<DeviceHidConnectionState> = MutableStateFlow(
        DeviceHidConnectionState()
    )
    val deviceHidConnectionState: StateFlow<DeviceHidConnectionState> = _deviceHidConnectionState

    // ---- Send Report ----

    fun sendReport(id: Int, bytes: ByteArray): Boolean {
        var success = false
        bluetoothDevice?.let { device ->
            bluetoothHidDevice?.let { hidDevice ->
                if (checkBluetoothConnectPermission(context)) {
                    success = hidDevice.sendReport(device, id, bytes)
                }
            }
        }
        return success
    }

    fun sendTextReport(text: String, virtualKeyboardLayout: VirtualKeyboardLayout): Boolean {
        var success = false
        bluetoothDevice?.let { device ->
            bluetoothHidDevice?.let { hidDevice ->
                if (checkBluetoothConnectPermission(context)) {
                    success = true
                    text.forEach {
                        if(!hidDevice.sendReport(device, KEYBOARD_REPORT_ID, virtualKeyboardLayout.getKeyboardKey(it))) {
                            success = false
                        }
                        hidDevice.sendReport(device, KEYBOARD_REPORT_ID, REMOTE_INPUT_NONE)
                    }
                }
            }
        }
        return success
    }
}