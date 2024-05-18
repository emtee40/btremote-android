package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.bluetooth.BluetoothHidProfile
import com.atharok.btremote.domain.entity.DeviceConnectionState
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository
import kotlinx.coroutines.flow.StateFlow

class BluetoothHidProfileRepositoryImpl(private val hidProfile: BluetoothHidProfile): BluetoothHidProfileRepository {

    override fun startHidProfile() {
        hidProfile.startBluetoothHidProfile()
    }

    override fun stopHidProfile() {
        hidProfile.stopBluetoothHidProfile()
    }

    override fun isRegisterAppFailedState(): StateFlow<Boolean> = hidProfile.registerAppFailedState

    override fun connectDevice(deviceAddress: String): Boolean {
        return hidProfile.connectDevice(deviceAddress)
    }

    override fun disconnectDevice(): Boolean {
        return hidProfile.disconnectDevice()
    }

    override fun getBluetoothDeviceName(): String? = hidProfile.getBluetoothDeviceName()

    override fun getDeviceConnectionState(): StateFlow<DeviceConnectionState> {
        return hidProfile.deviceConnectionState
    }

    override fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return hidProfile.sendReport(id, bytes)
    }

    override fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean {
        return hidProfile.sendTextReport(text, keyboardLayout)
    }
}