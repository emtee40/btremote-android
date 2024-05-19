package com.atharok.btremote.common.extensions

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.atharok.btremote.common.utils.DATA_STORE_PREFERENCES_SETTINGS_NAME

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_PREFERENCES_SETTINGS_NAME)

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}