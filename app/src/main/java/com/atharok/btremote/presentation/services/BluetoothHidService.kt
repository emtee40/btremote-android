package com.atharok.btremote.presentation.services

import android.app.Notification
import android.app.PendingIntent
import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import com.atharok.btremote.R
import com.atharok.btremote.common.utils.NOTIFICATION_CHANNEL_ID
import com.atharok.btremote.domain.usecases.BluetoothHidServiceUseCase
import com.atharok.btremote.presentation.activities.MainActivity
import org.koin.android.ext.android.inject


class BluetoothHidService : Service() {

    private val useCase: BluetoothHidServiceUseCase by inject()

    override fun onCreate() {
        super.onCreate()
        registerReceiver()
    }

    override fun onDestroy() {
        stopBluetoothHidProfile()
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        stopBluetoothHidProfile()
        stopSelf()
        super.onTaskRemoved(rootIntent)
    }

    override fun onBind(intent: Intent?): IBinder = Binder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        try {
            startForeground(1, createNotification())
            startBluetoothHidProfile()
        } catch (exception: Exception) {
            stopSelf()
        }

        return super.onStartCommand(intent, flags, startId)
    }

    // ---- Notification ----

    private fun createNotification(): Notification {
        return NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.notification_icon)
            .setContentTitle(getString(R.string.app_name))
            .setContentText(getString(R.string.connected_on, "tmp"/*"$deviceName ($deviceAddress)"*/))
            .setOngoing(true)
            .setContentIntent(openApplication())
            .build()
    }

    private fun openApplication(): PendingIntent {
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        return PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    // ---- Bluetooth HID Profile ----

    private fun startBluetoothHidProfile() {
        useCase.startHidProfile()
    }

    private fun stopBluetoothHidProfile() {
        useCase.stopHidProfile()
        unregisterReceiver()
    }

    // ---- BroadcastReceiver ----

    private val bluetoothStateChangeReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            when(intent?.action) {
                BluetoothAdapter.ACTION_STATE_CHANGED -> {
                    val bluetoothState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR)
                    if (bluetoothState == BluetoothAdapter.STATE_OFF) {
                        stopBluetoothHidProfile()
                        stopSelf() // Stop service if bluetooth is disabled
                    }
                }
            }
        }
    }

    private fun registerReceiver() {
        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        registerReceiver(bluetoothStateChangeReceiver, filter)
    }

    private fun unregisterReceiver() {
        try {
            unregisterReceiver(bluetoothStateChangeReceiver)
        } catch (e: java.lang.RuntimeException) {
            Log.i("unregisterReceiver()", "Receiver already unregister")
        }
    }
}