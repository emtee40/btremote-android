package com.atharok.btremote.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import com.atharok.btremote.common.utils.KEYBOARD_REPORT_ID
import com.atharok.btremote.common.utils.MOUSE_REPORT_ID
import com.atharok.btremote.common.utils.REMOTE_REPORT_ID
import com.atharok.btremote.domain.entity.DeviceEntity
import com.atharok.btremote.domain.entity.DeviceHidConnectionState
import com.atharok.btremote.domain.entity.MouseInput
import com.atharok.btremote.domain.entity.keyboard.layout.KeyboardLayout
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

    fun isBluetoothHidProfileConnected(): StateFlow<Boolean> {
        return useCase.isBluetoothHidProfileConnected()
    }

    fun hasBluetoothHidProfileConnectionFailed(): StateFlow<Boolean> {
        return useCase.hasBluetoothHidProfileConnectionFailed()
    }

    fun connectDevice(device: DeviceEntity): Boolean = useCase.connectDevice(device.macAddress)

    fun disconnectDevice(): Boolean = useCase.disconnectDevice()

    fun getBluetoothDeviceName(): String? = useCase.getBluetoothDeviceName()

    fun getDeviceHidConnectionState(): StateFlow<DeviceHidConnectionState> =
        useCase.getDeviceHidConnectionState()

    // ---- Send ----

    fun sendRemoteKeyReport(bytes: ByteArray): Boolean = sendReport(REMOTE_REPORT_ID, bytes)

    fun sendMouseKeyReport(bytes: ByteArray): Boolean = sendReport(MOUSE_REPORT_ID, bytes)

    fun sendMouseKeyReport(
        input: MouseInput = MouseInput.NONE,
        x: Float = 0f,
        y: Float = 0f,
        wheel: Float
    ): Boolean {
        val bytes: ByteArray = when(input) {
            MouseInput.NONE ->
                byteArrayOf(0x00, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.PAD_TAP, MouseInput.MOUSE_CLICK_LEFT ->
                byteArrayOf(0x01, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.MOUSE_CLICK_RIGHT ->
                byteArrayOf(0x02, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.MOUSE_CLICK_MIDDLE ->
                byteArrayOf(0x04, x.roundToInt().toByte(), y.roundToInt().toByte(), 0x00)
            MouseInput.MOUSE_WHEEL ->
                byteArrayOf(0x00, 0x00, 0x00, wheel.roundToInt().toByte())
        }
        return sendReport(MOUSE_REPORT_ID, bytes)
    }

    fun sendKeyboardKeyReport(bytes: ByteArray): Boolean = sendReport(KEYBOARD_REPORT_ID, bytes)

    fun sendTextReport(text: String, keyboardLayout: KeyboardLayout): Boolean {
        return useCase.sendTextReport(text, keyboardLayout)
    }

    private fun sendReport(id: Int, bytes: ByteArray): Boolean {
        return useCase.sendReport(id, bytes)
    }
}