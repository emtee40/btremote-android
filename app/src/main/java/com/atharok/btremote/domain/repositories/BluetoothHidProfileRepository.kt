package com.atharok.btremote.domain.repositories

import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import kotlinx.coroutines.flow.StateFlow

interface BluetoothHidProfileRepository {

    fun startHidProfile()

    fun stopHidProfile()

    fun isBluetoothHidProfileConnected(): StateFlow<Boolean>

    fun hasBluetoothHidProfileConnectionFailed(): StateFlow<Boolean>

    fun connectDevice(deviceAddress: String): Boolean

    fun disconnectDevice(): Boolean

    fun getBluetoothDeviceName(): String?

    fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState>

    fun sendReport(id: Int, bytes: ByteArray): Boolean

    fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean
}