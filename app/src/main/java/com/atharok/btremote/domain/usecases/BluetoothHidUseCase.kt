package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidUseCase(private val repository: BluetoothHidProfileRepository) {

    fun isBluetoothHidProfileConnected(): StateFlow<Boolean> {
        return repository.isBluetoothHidProfileConnected()
    }

    fun hasBluetoothHidProfileConnectionFailed(): StateFlow<Boolean> {
        return repository.hasBluetoothHidProfileConnectionFailed()
    }

    fun connectDevice(deviceAddress: String): Boolean {
        return repository.connectDevice(deviceAddress)
    }

    fun disconnectDevice(): Boolean {
        return repository.disconnectDevice()
    }

    fun getBluetoothDeviceName(): String? = repository.getBluetoothDeviceName()

    fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState> {
        return repository.getDeviceHidConnectionState()
    }

    fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return repository.sendReport(id, bytes)
    }

    fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean {
        return repository.sendTextReport(text, keyboardLayout)
    }
}