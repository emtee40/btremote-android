package com.atharok.btremote.ui.views.mouse

import android.view.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import com.atharok.btremote.presentation.viewmodel.GyroscopeSensorViewModel
import org.koin.androidx.compose.koinViewModel

private const val OFFSET = 10

@Composable
fun MouseGyroscope(
    mouseSpeed: Float,
    onMousePositionChange: (Float, Float) -> Unit,
    gyroscopeSensorViewModel: GyroscopeSensorViewModel = koinViewModel()
) {

    LaunchedEffect(Unit) {
        gyroscopeSensorViewModel.startListening()
    }

    DisposableEffect(Unit) {
        onDispose {
            gyroscopeSensorViewModel.stopListening()
        }
    }

    LaunchedEffect(gyroscopeSensorViewModel.positions.value) {
        val positions: Triple<Float, Float, Float> = gyroscopeSensorViewModel.positions.value
        when(gyroscopeSensorViewModel.getDisplayRotation()) {

            Surface.ROTATION_0 -> {
                onMousePositionChange(
                    positions.third * OFFSET * mouseSpeed * (-1),
                    positions.first * OFFSET * mouseSpeed * (-1)
                )
            }

            Surface.ROTATION_180 -> {
                onMousePositionChange(
                    positions.third * OFFSET * mouseSpeed * (-1),
                    positions.first * OFFSET * mouseSpeed
                )
            }

            Surface.ROTATION_270 -> {
                onMousePositionChange(
                    positions.third * OFFSET * mouseSpeed * (-1),
                    positions.second * OFFSET * mouseSpeed * (-1),
                )
            }

            Surface.ROTATION_90 -> {
                onMousePositionChange(
                    positions.third * OFFSET * mouseSpeed * (-1),
                    positions.second * OFFSET * mouseSpeed,
                )
            }
        }
    }
}