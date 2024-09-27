package com.atharok.btremote.data.repositories

import androidx.compose.runtime.State
import com.atharok.btremote.data.sensor.GyroscopeSensor
import com.atharok.btremote.domain.repositories.GyroscopeSensorRepository

class GyroscopeSensorRepositoryImpl(private val sensor: GyroscopeSensor): GyroscopeSensorRepository {

    override fun startListening() {
        sensor.startListening()
    }

    override fun stopListening() {
        sensor.stopListening()
    }

    override fun getDisplayRotation(): Int = sensor.getDisplayRotation()

    override fun getPositions(): State<Triple<Float, Float, Float>> = sensor.gyroscopePositionsState
}