package com.atharok.btremote.ui.app

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.atharok.btremote.common.extensions.getActivity
import com.atharok.btremote.domain.entity.remoteInput.MouseAction
import com.atharok.btremote.presentation.viewmodel.BluetoothHidViewModel
import com.atharok.btremote.presentation.viewmodel.BluetoothViewModel
import com.atharok.btremote.presentation.viewmodel.SettingsViewModel
import com.atharok.btremote.ui.components.BluetoothHidLifecycle
import com.atharok.btremote.ui.components.OnLifecycleEvent
import com.atharok.btremote.ui.navigation.AppNavDestination
import com.atharok.btremote.ui.navigation.AppNavHost
import com.atharok.btremote.ui.navigation.navigateTo
import com.atharok.btremote.ui.screens.BluetoothActivationScreen
import com.atharok.btremote.ui.screens.BluetoothNotSupportScreen
import com.atharok.btremote.ui.screens.BluetoothPermissionsScreen
import com.atharok.btremote.ui.screens.BluetoothScanningScreen
import com.atharok.btremote.ui.screens.DevicesSelectionScreen
import com.atharok.btremote.ui.screens.RemoteScreen
import com.atharok.btremote.ui.screens.SettingsScreen
import com.atharok.btremote.ui.screens.ThirdLibrariesScreen
import com.atharok.btremote.ui.theme.BtRemoteTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComposeRoot(
    navController: NavHostController = rememberNavController(),
    settingsViewModel: SettingsViewModel = koinViewModel(),
    bluetoothViewModel: BluetoothViewModel = koinViewModel(),
    hidViewModel: BluetoothHidViewModel = koinViewModel(),
    openSettings: () -> Unit = {
        navController.navigateTo(AppNavDestination.SettingsDestination.route)
    }
) {
    BtRemoteTheme(settingsViewModel) {
        Surface(modifier = Modifier.fillMaxSize()) {
            if(!bluetoothViewModel.isBluetoothSupported) {
                BluetoothNotSupportScreen()
            } else {

                val context: Context = LocalContext.current

                BluetoothHidLifecycle(
                    isEnabled = bluetoothViewModel.isBluetoothEnable(),
                    isBluetoothServiceStartedFlow = hidViewModel.isBluetoothServiceStarted(),
                    isBluetoothHidProfileRegisteredFlow = hidViewModel.isBluetoothHidProfileRegistered(),
                    bluetoothDeviceHidConnectionStateFlow = hidViewModel.getDeviceHidConnectionState()
                ) { isBluetoothEnabled, isBluetoothServiceStarted, isBluetoothHidProfileRegistered, bluetoothDeviceHidConnectionState ->

                    // ---- NavHost ----

                    AppNavHost(
                        navController = navController,

                        bluetoothPermissionsScreen = {
                            BluetoothPermissionsScreen(
                                permissions = bluetoothViewModel.getBluetoothPermissions(),
                                arePermissionsGranted = { bluetoothViewModel.areBluetoothPermissionsGranted() },
                                onPermissionsGranted = {
                                    navController.navigate(AppNavDestination.BluetoothActivationDestination.route) {
                                        popUpTo(0) {
                                            this.saveState = false
                                        }
                                    }
                                },
                                openSettings = openSettings,
                                modifier = Modifier
                            )
                        },

                        settingsScreen = {
                            SettingsScreen(
                                navigateUp = { navController.navigateUp() },
                                openThirdLibrariesScreen = {
                                    navController.navigateTo(AppNavDestination.ThirdLibrariesDestination.route)
                                },
                                settingsViewModel = settingsViewModel,
                                modifier = Modifier
                            )
                        },

                        thirdLibrariesScreen = {
                            ThirdLibrariesScreen(
                                navigateUp = { navController.navigateUp() },
                                modifier = Modifier
                            )
                        },

                        bluetoothActivationScreen = {
                            BluetoothActivationScreen(
                                isBluetoothEnabled = isBluetoothEnabled,
                                openBluetoothDeviceSelectionScreen = {
                                    navController.navigateTo(AppNavDestination.BluetoothDeviceSelectionDestination.route)
                                },
                                openSettings = openSettings
                            )
                        },

                        bluetoothDeviceSelectionScreen = {
                            DevicesSelectionScreen(
                                isBluetoothEnabled = isBluetoothEnabled,
                                isBluetoothServiceStarted = isBluetoothServiceStarted,
                                isBluetoothHidProfileRegistered = isBluetoothHidProfileRegistered,
                                bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                                closeApp = { context.getActivity()?.finish() },
                                navigateUp = { navController.navigateUp() },
                                startHidService = { hidViewModel.startService(context) },
                                stopHidService = { hidViewModel.stopService(context) },
                                devicesFlow = bluetoothViewModel.devicesEntityObserver,
                                findBondedDevices = { bluetoothViewModel.findBondedDevices() },
                                connectDevice = { device ->
                                    if(!hidViewModel.connectDevice(device)) {
                                        hidViewModel.disconnectDevice()
                                        hidViewModel.connectDevice(device)
                                    }
                                },
                                disconnectDevice = { hidViewModel.disconnectDevice() },
                                openRemoteScreen = {
                                    navController.navigateTo(AppNavDestination.BluetoothRemoteDestination.route)
                                },
                                openBluetoothScanningDeviceScreen = {
                                    navController.navigateTo(AppNavDestination.BluetoothDeviceScanningDestination.route)
                                },
                                openSettings = openSettings,
                                modifier = Modifier
                            )
                        },

                        bluetoothDeviceScanningScreen = {
                            BluetoothScanningScreen(
                                bluetoothScanningPermissions = bluetoothViewModel.getBluetoothScanningPermissions(),
                                areBluetoothScanningPermissionsGranted = {
                                    bluetoothViewModel.areBluetoothScanningPermissionsGranted()
                                },
                                isBluetoothEnabled = isBluetoothEnabled,
                                isBluetoothServiceStarted = isBluetoothServiceStarted,
                                bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                                navigateUp = {
                                    bluetoothViewModel.cancelDiscovery()
                                    navController.navigateUp()
                                },
                                isDiscoveringFlow = bluetoothViewModel.isDiscovering,
                                startDiscovery = { bluetoothViewModel.startDiscovery() },
                                cancelDiscovery = { bluetoothViewModel.cancelDiscovery() },
                                connectToDevice = { device ->
                                    bluetoothViewModel.cancelDiscovery()
                                    hidViewModel.connectDevice(device)
                                },
                                disconnectDevice = { hidViewModel.disconnectDevice() },
                                openRemoteScreen = {
                                    navController.navigateTo(AppNavDestination.BluetoothRemoteDestination.route)
                                },
                                openSettings = openSettings,
                                modifier = Modifier
                            )
                        },

                        bluetoothRemoteScreen = {
                            RemoteScreen(
                                deviceName = bluetoothDeviceHidConnectionState.deviceName,
                                isBluetoothServiceStarted = isBluetoothServiceStarted,
                                bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                                navigateUp = { navController.navigateUp() },
                                closeApp = { context.getActivity()?.finish() },
                                openSettings = openSettings,
                                settingsViewModel = settingsViewModel,
                                disconnectDevice = {
                                    hidViewModel.disconnectDevice()
                                },
                                forceDisconnectDevice = {
                                    hidViewModel.disconnectDevice()
                                    hidViewModel.stopService(context)
                                },
                                sendRemoteKeyReport = { bytes -> hidViewModel.sendRemoteKeyReport(bytes) },
                                sendMouseKeyReport = { input: MouseAction, x: Float, y: Float, wheel: Float -> hidViewModel.sendMouseKeyReport(input, x, y, wheel) },
                                sendKeyboardKeyReport = { bytes -> hidViewModel.sendKeyboardKeyReport(bytes) },
                                sendTextReport = { text, keyboardLayout -> hidViewModel.sendTextReport(text, keyboardLayout) },
                                modifier = Modifier
                            )
                        }
                    )

                    // ---- Lifecycle ----

                    OnLifecycleEvent { _, event ->
                        when(event) {
                            Lifecycle.Event.ON_RESUME -> {
                                // Si une permission a été retirée par l'utilisateur pendant l'utilisation de l'application, on affiche l'écran de permissions
                                if(!bluetoothViewModel.areBluetoothPermissionsGranted() && navController.currentDestination?.route != AppNavDestination.BluetoothPermissionsDestination.route) {
                                    navController.navigate(AppNavDestination.BluetoothPermissionsDestination.route) {
                                        popUpTo(0) {
                                            this.saveState = false
                                        }
                                    }
                                }
                            }
                            Lifecycle.Event.ON_STOP -> {
                                if(navController.currentDestination?.route == AppNavDestination.BluetoothDeviceSelectionDestination.route) {
                                    if(isBluetoothServiceStarted) {
                                        hidViewModel.stopService(context)
                                    }
                                }
                            }
                            else -> {}
                        }
                    }
                }
            }
        }
    }
}