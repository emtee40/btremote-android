package com.atharok.btremote.ui.navigation

sealed class AppNavDestination(val route: String) {
    data object BluetoothPermissionsDestination: AppNavDestination(route = "bluetooth_permissions_route")
    data object SettingsDestination: AppNavDestination(route = "settings_route")
    data object ThirdLibrariesDestination: AppNavDestination(route = "third_libraries_route")
    data object BluetoothActivationDestination: AppNavDestination(route = "bluetooth_activation_route")
    data object BluetoothDeviceSelectionDestination: AppNavDestination(route = "bluetooth_device_selection_route")
    data object BluetoothDeviceScanningDestination: AppNavDestination(route = "bluetooth_device_scanning_route")
    data object BluetoothRemoteDestination: AppNavDestination(route = "bluetooth_remote_route")
}