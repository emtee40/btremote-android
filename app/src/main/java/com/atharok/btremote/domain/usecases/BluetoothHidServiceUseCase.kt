package com.atharok.btremote.domain.usecases

import com.atharok.btremote.domain.repositories.BluetoothHidProfileRepository

class BluetoothHidServiceUseCase(private val repository: BluetoothHidProfileRepository) {

    fun startHidProfile() {
        repository.startHidProfile()
    }

    fun stopHidProfile() {
        repository.stopHidProfile()
    }
}