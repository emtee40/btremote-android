package com.atharok.btremote.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import com.atharok.btremote.domain.usecases.GyroscopeSensorUseCase

class GyroscopeSensorViewModel(
    private val useCase: GyroscopeSensorUseCase
): ViewModel() {

    fun startListening() {
        useCase.startListening()
    }

    fun stopListening() {
        useCase.stopListening()
    }

    fun getDisplayRotation(): Int = useCase.getDisplayRotation()

    val positions: State<Triple<Float, Float, Float>> get() = useCase.getPositions()
}