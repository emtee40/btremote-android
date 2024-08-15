package com.atharok.btremote.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.atharok.btremote.common.utils.KEYBOARD_REPORT_ID
import com.atharok.btremote.common.utils.MOUSE_REPORT_ID
import com.atharok.btremote.common.utils.REMOTE_REPORT_ID
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.remoteInput.MouseAction
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import com.atharok.btremote.domain.usecases.BluetoothHidUseCase
import com.atharok.btremote.presentation.services.BluetoothHidService
import kotlinx.coroutines.flow.StateFlow
import kotlin.math.roundToInt

class BluetoothHidViewModel(
    private val useCase: BluetoothHidUseCase
): ViewModel() {

    fun startService(context: Context) {
        val serviceIntent = Intent(context, BluetoothHidService::class.java)
        context.startForegroundService(serviceIntent)
    }

    fun stopService(context: Context) {
        val serviceIntent = Intent(context, BluetoothHidService::class.java)
        context.stopService(serviceIntent)
    }

    fun isBluetoothServiceStarted(): StateFlow<Boolean> {
        return useCase.isBluetoothServiceStarted()
    }

    fun isBluetoothHidProfileRegistered(): StateFlow<Boolean> {
        return useCase.isBluetoothHidProfileRegistered()
    }

    fun connectDevice(device: DeviceEntity): Boolean = useCase.connectDevice(device.macAddress)

    fun disconnectDevice(): Boolean = useCase.disconnectDevice()

    fun getBluetoothDeviceName(): String? = useCase.getBluetoothDeviceName()

    fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState> =
        useCase.getDeviceHidConnectionState()

    // ---- Send ----

    fun sendRemoteKeyReport(bytes: ByteArray): Boolean = sendReport(REMOTE_REPORT_ID, bytes)

    fun sendMouseKeyReport(
        input: MouseAction = MouseAction.NONE,
        x: Float = 0f,
        y: Float = 0f,
        wheel: Float
    ): Boolean {
        val bytes: ByteArray = byteArrayOf(input.byte, x.roundToInt().toByte(), y.roundToInt().toByte(), wheel.roundToInt().toByte())
        return sendReport(MOUSE_REPORT_ID, bytes)
    }

    fun sendKeyboardKeyReport(bytes: ByteArray): Boolean = sendReport(KEYBOARD_REPORT_ID, bytes)

    fun sendTextReport(text: String, virtualKeyboardLayout: VirtualKeyboardLayout): Boolean {
        return useCase.sendTextReport(text, virtualKeyboardLayout)
    }

    private fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return useCase.sendReport(id, bytes)
    }
}