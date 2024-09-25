package com.atharok.btremote.common.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import com.atharok.btremote.domain.entity.remoteInput.keyboard.KeyboardLanguage
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.AdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.BRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.CSAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.DEAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.ESAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.FRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.GRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.PLAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.PTAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.RUAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.TRAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.UKAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.advancedKeyboard.USAdvancedKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.BRVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.CSVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.DEVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.ESVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.FRVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.GRVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.PLVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.PTVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.RUVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.TRVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.UKVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.USVirtualKeyboardLayout
import com.atharok.btremote.domain.entity.remoteInput.keyboard.virtualKeyboard.VirtualKeyboardLayout
import org.koin.mp.KoinPlatform.getKoin

fun isDynamicColorsAvailable(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S

fun checkBluetoothConnectPermission(context: Context): Boolean = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) == PackageManager.PERMISSION_GRANTED
    else -> ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH) == PackageManager.PERMISSION_GRANTED
}

fun checkBluetoothScanPermission(context: Context): Boolean = when {
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_SCAN) == PackageManager.PERMISSION_GRANTED
    else -> ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_ADMIN) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
}

fun getKeyboardLayout(language: KeyboardLanguage): VirtualKeyboardLayout {
    return when(language) {
        KeyboardLanguage.ENGLISH_US -> getKoin().get<USVirtualKeyboardLayout>()
        KeyboardLanguage.ENGLISH_UK -> getKoin().get<UKVirtualKeyboardLayout>()
        KeyboardLanguage.SPANISH -> getKoin().get<ESVirtualKeyboardLayout>()
        KeyboardLanguage.FRENCH -> getKoin().get<FRVirtualKeyboardLayout>()
        KeyboardLanguage.GERMAN -> getKoin().get<DEVirtualKeyboardLayout>()
        KeyboardLanguage.RUSSIAN -> getKoin().get<RUVirtualKeyboardLayout>()
        KeyboardLanguage.CZECH -> getKoin().get<CSVirtualKeyboardLayout>()
        KeyboardLanguage.POLISH -> getKoin().get<PLVirtualKeyboardLayout>()
        KeyboardLanguage.PORTUGUESE -> getKoin().get<PTVirtualKeyboardLayout>()
        KeyboardLanguage.BRAZILIAN -> getKoin().get<BRVirtualKeyboardLayout>()
        KeyboardLanguage.GREEK -> getKoin().get<GRVirtualKeyboardLayout>()
        KeyboardLanguage.TURKISH -> getKoin().get<TRVirtualKeyboardLayout>()
    }
}

fun getAdvancedKeyboardLayout(language: KeyboardLanguage): AdvancedKeyboardLayout {
    return when(language) {
        KeyboardLanguage.ENGLISH_US -> getKoin().get<USAdvancedKeyboardLayout>()
        KeyboardLanguage.ENGLISH_UK -> getKoin().get<UKAdvancedKeyboardLayout>()
        KeyboardLanguage.SPANISH -> getKoin().get<ESAdvancedKeyboardLayout>()
        KeyboardLanguage.FRENCH -> getKoin().get<FRAdvancedKeyboardLayout>()
        KeyboardLanguage.GERMAN -> getKoin().get<DEAdvancedKeyboardLayout>()
        KeyboardLanguage.RUSSIAN -> getKoin().get<RUAdvancedKeyboardLayout>()
        KeyboardLanguage.CZECH -> getKoin().get<CSAdvancedKeyboardLayout>()
        KeyboardLanguage.POLISH -> getKoin().get<PLAdvancedKeyboardLayout>()
        KeyboardLanguage.PORTUGUESE -> getKoin().get<PTAdvancedKeyboardLayout>()
        KeyboardLanguage.BRAZILIAN -> getKoin().get<BRAdvancedKeyboardLayout>()
        KeyboardLanguage.GREEK -> getKoin().get<GRAdvancedKeyboardLayout>()
        KeyboardLanguage.TURKISH -> getKoin().get<TRAdvancedKeyboardLayout>()
    }
}