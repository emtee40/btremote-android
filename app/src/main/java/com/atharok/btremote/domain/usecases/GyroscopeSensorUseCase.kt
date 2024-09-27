package com.atharok.btremote.domain.usecases

import androidx.compose.runtime.State
import com.atharok.btremote.domain.repositories.GyroscopeSensorRepository

class GyroscopeSensorUseCase(private val repository: GyroscopeSensorRepository) {

    fun startListening() {
        repository.startListening()
    }

    fun stopListening() {
        repository.stopListening()
    }

    fun getDisplayRotation(): Int = repository.getDisplayRotation()

    fun getPositions(): State<Triple<Float, Float, Float>> = repository.getPositions()
}