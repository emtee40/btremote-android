package com.atharok.btremote.data.repositories

import com.atharok.btremote.data.bluetooth.BluetoothInteractions
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.repositories.BluetoothRepository

class BluetoothRepositoryImpl(
    private val bluetoothInteractions: BluetoothInteractions
): BluetoothRepository {
    override fun isBluetoothSupported(): Boolean = bluetoothInteractions.isBluetoothSupported()

    override fun isBluetoothEnabled(): Boolean = bluetoothInteractions.isBluetoothEnabled()

    override fun getBluetoothPermissions(): Array<String> {
        return bluetoothInteractions.bluetoothMandatoryPermissions
    }

    override fun areBluetoothPermissionsGranted(): Boolean {
        return bluetoothInteractions.areBluetoothMandatoryPermissionsGranted()
    }

    override fun getBluetoothScanningPermissions(): Array<String> {
        return bluetoothInteractions.bluetoothScanningPermissions
    }

    override fun areBluetoothScanningPermissionsGranted(): Boolean {
        return bluetoothInteractions.areBluetoothScanningPermissionsGranted()
    }

    override fun getBondedDevices(): List<DeviceEntity> = bluetoothInteractions.getBondedDevices()

    override fun startDiscovery(): Boolean = bluetoothInteractions.startDiscoveryDevices()

    override fun cancelDiscovery(): Boolean = bluetoothInteractions.cancelDiscoveryDevices()
}