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
import com.atharok.btremote.domain.entity.MouseInput
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
import com.atharok.btremote.ui.screens.ConnectingScreen
import com.atharok.btremote.ui.screens.DevicesSelectionScreen
import com.atharok.btremote.ui.screens.RemoteScreen
import com.atharok.btremote.ui.screens.SettingsScreen
import com.atharok.btremote.ui.screens.ThirdLibrariesScreen
import com.atharok.btremote.ui.theme.BtRemoteTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainActivityRootView(
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
                    isBluetoothHidProfileConnectedFlow = hidViewModel.isBluetoothHidProfileConnected(),
                    hasBluetoothHidProfileConnectionFailedFlow = hidViewModel.hasBluetoothHidProfileConnectionFailed(),
                    bluetoothDeviceHidConnectionStateFlow = hidViewModel.getDeviceHidConnectionState()
                ) { isBluetoothEnabled, isBluetoothHidProfileConnected, hasBluetoothHidProfileConnectionFailed, bluetoothDeviceHidConnectionState ->

                    // ---- NavHost ----

                    AppNavHost(
                        navController = navController,

                        bluetoothPermissionsScreen = {
                            BluetoothPermissionsScreen(
                                permissions = bluetoothViewModel.getPermissions(),
                                arePermissionsGranted = { bluetoothViewModel.arePermissionsGranted() },
                                doAfterGrantPermissions = {
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
                                    navController.navigate(AppNavDestination.ThirdLibrariesDestination.route)
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
                                isBluetoothHidProfileConnected = isBluetoothHidProfileConnected,
                                hasBluetoothHidProfileConnectionFailed = hasBluetoothHidProfileConnectionFailed,
                                bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                                closeApp = { context.getActivity()?.finish() },
                                navigateUp = { navController.navigateUp() },
                                startHidService = { hidViewModel.startService(context) },
                                stopHidService = { hidViewModel.stopService(context) },
                                devicesFlow = bluetoothViewModel.devicesEntityObserver,
                                findBondedDevices = { bluetoothViewModel.findBondedDevices() },
                                connectDevice = { device -> hidViewModel.connectDevice(device) },
                                openConnectingScreen = {
                                    navController.navigateTo(AppNavDestination.BluetoothDeviceConnectingDestination.route)
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
                                isBluetoothEnabled = isBluetoothEnabled,
                                isBluetoothHidProfileConnected = isBluetoothHidProfileConnected,
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
                                openConnectingScreen = {
                                    navController.navigateTo(AppNavDestination.BluetoothDeviceConnectingDestination.route)
                                },
                                openSettings = openSettings,
                                modifier = Modifier
                            )
                        },

                        bluetoothDeviceConnectingScreen = {
                            ConnectingScreen(
                                deviceName = bluetoothDeviceHidConnectionState.deviceName,
                                bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                                navigateUp = { navController.navigateUp() },
                                openRemoteScreen = {
                                    navController.navigateTo(AppNavDestination.BluetoothRemoteDestination.route)
                                },
                                cancelConnection = {
                                    // If the disconnection fails, we stop the service.
                                    if(!hidViewModel.disconnectDevice()) {
                                        hidViewModel.stopService(context)
                                    }
                                },
                                modifier = Modifier.fillMaxSize()
                            )
                        },
                        bluetoothRemoteScreen = {
                            RemoteScreen(
                                deviceName = bluetoothDeviceHidConnectionState.deviceName,
                                bluetoothDeviceHidConnectionState = bluetoothDeviceHidConnectionState,
                                navigateUp = { navController.navigateUp() },
                                closeApp = { context.getActivity()?.finish() },
                                openSettings = openSettings,
                                disconnectDevice = {
                                    // If the disconnection fails, we stop the service.
                                    if(!hidViewModel.disconnectDevice()) {
                                        hidViewModel.stopService(context)
                                    }
                                },
                                sendRemoteKeyReport = { bytes -> hidViewModel.sendRemoteKeyReport(bytes) },
                                sendMouseKeyReport = { input: MouseInput, x: Float, y: Float, wheel: Float -> hidViewModel.sendMouseKeyReport(input, x, y, wheel) },
                                sendKeyboardKeyReport = { bytes -> hidViewModel.sendKeyboardKeyReport(bytes) },
                                sendTextReport = { text, keyboardLayout -> hidViewModel.sendTextReport(text, keyboardLayout) },
                                keyboardLanguageFlow = settingsViewModel.keyboardLanguage,
                                getKeyboardLayout = { settingsViewModel.getKeyboardLayout(it) },
                                mustClearInputFieldFlow = settingsViewModel.mustClearInputField(),
                                mouseSpeedFlow = settingsViewModel.getMouseSpeed(),
                                shouldInvertMouseScrollingDirectionFlow = settingsViewModel.shouldInvertMouseScrollingDirection(),
                                modifier = Modifier
                            )
                        }
                    )

                    // ---- Lifecycle ----

                    OnLifecycleEvent { _, event ->
                        when(event) {
                            Lifecycle.Event.ON_RESUME -> {
                                // Si une permission a été retirée par l'utilisateur pendant l'utilisation de l'application, on affiche l'écran de permissions
                                if(!bluetoothViewModel.arePermissionsGranted() && navController.currentDestination?.route != AppNavDestination.BluetoothPermissionsDestination.route) {
                                    navController.navigate(AppNavDestination.BluetoothPermissionsDestination.route) {
                                        popUpTo(0) {
                                            this.saveState = false
                                        }
                                    }
                                }
                            }
                            Lifecycle.Event.ON_STOP -> {
                                if(navController.currentDestination?.route == AppNavDestination.BluetoothDeviceSelectionDestination.route) {
                                    if(isBluetoothHidProfileConnected) {
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