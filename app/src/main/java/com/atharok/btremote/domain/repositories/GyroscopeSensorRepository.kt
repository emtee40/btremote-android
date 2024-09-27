package com.atharok.btremote.domain.repositories

import androidx.compose.runtime.State

interface GyroscopeSensorRepository {

    fun startListening()

    fun stopListening()

    fun getDisplayRotation(): Int

    fun getPositions(): State<Triple<Float, Float, Float>>
}