package com.atharok.btremote.data.sensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.hardware.display.DisplayManager
import android.view.Display
import android.view.Surface
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

class GyroscopeSensor(
    private val sensorManager: SensorManager,
    displayManager: DisplayManager
): SensorEventListener {

    private val gyroscope: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
    private val display: Display? = displayManager.getDisplay(Display.DEFAULT_DISPLAY)

    private val _gyroscopePositionsState: MutableState<Triple<Float, Float, Float>> = mutableStateOf(Triple(0f, 0f, 0f))
    val gyroscopePositionsState: State<Triple<Float, Float, Float>> = _gyroscopePositionsState

    fun startListening() {
        gyroscope?.also {
            sensorManager.registerListener(this, it, 10000)
        }
    }

    fun stopListening() {
        sensorManager.unregisterListener(this)
    }

    fun getDisplayRotation(): Int = display?.rotation ?: Surface.ROTATION_0

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            _gyroscopePositionsState.value = Triple(event.values[0], event.values[1], event.values[2])
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
}