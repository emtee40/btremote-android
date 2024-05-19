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
import com.atharok.btremote.common.utils.checkBluetoothConnectPermission
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidProfile(
    private val context: Context,
    private val adapter: BluetoothAdapter?,
    private val hidSettings: BluetoothHidDeviceAppSdpSettings
): BluetoothHidDevice.Callback(), ServiceListener {

    private var bluetoothHidDevice: BluetoothHidDevice? = null

    private val _isBluetoothHidProfileConnected: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isBluetoothHidProfileConnected: StateFlow<Boolean> = _isBluetoothHidProfileConnected

    private val _hasBluetoothHidProfileConnectionFailed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val hasBluetoothHidProfileConnectionFailed: StateFlow<Boolean> = _hasBluetoothHidProfileConnectionFailed

    // ---- HID Profile ----

    fun startBluetoothHidProfile() {
        _hasBluetoothHidProfileConnectionFailed.value = false
        adapter?.getProfileProxy(context, this, BluetoothProfile.HID_DEVICE)
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
        _isBluetoothHidProfileConnected.value = false
    }

    // ---- BluetoothProfile.ServiceListener implementation ----

    override fun onServiceConnected(i: Int, bluetoothProfile: BluetoothProfile?) {
        if(i == BluetoothProfile.HID_DEVICE) {
            bluetoothHidDevice = bluetoothProfile as? BluetoothHidDevice?
            bluetoothHidDevice?.let { hidDevice ->
                if (checkBluetoothConnectPermission(context)) {
                    val success = hidDevice.registerApp(hidSettings, null, null, Runnable::run, this)
                    _hasBluetoothHidProfileConnectionFailed.value = !success
                }
            }
        }
        _isBluetoothHidProfileConnected.value = true
    }

    override fun onServiceDisconnected(i: Int) {}

    // ---- BluetoothHidDevice.Callback implementation ----

    override fun onAppStatusChanged(pluggedDevice: BluetoothDevice?, registered: Boolean) {
        super.onAppStatusChanged(pluggedDevice, registered)
    }

    override fun onConnectionStateChanged(device: BluetoothDevice?, state: Int) {
        super.onConnectionStateChanged(device, state)
        if (checkBluetoothConnectPermission(context)) {
            _deviceHidConnectionState.value = DeviceHidConnectionState(state, device?.name ?: "")
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

    fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean {
        var success = false
        bluetoothDevice?.let { device ->
            bluetoothHidDevice?.let { hidDevice ->
                if (checkBluetoothConnectPermission(context)) {
                    success = true
                    text.forEach {
                        if(!hidDevice.sendReport(device, KEYBOARD_REPORT_ID, keyboardLayout.getKeyboardKey(it))) {
                            success = false
                        }
                        hidDevice.sendReport(device, KEYBOARD_REPORT_ID, KeyboardLayout.KEYBOARD_KEY_NONE)
                    }
                }
            }
        }
        return success
    }
}