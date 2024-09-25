package com.atharok.btremote.domain.entity.remoteInput.keyboard

import androidx.annotation.StringRes
import com.atharok.btremote.R

enum class KeyboardLanguage(@StringRes val language: Int) {
    ENGLISH_US(R.string.keyboard_us),
    ENGLISH_UK(R.string.keyboard_uk),
    SPANISH(R.string.keyboard_es),
    FRENCH(R.string.keyboard_fr),
    GERMAN(R.string.keyboard_de),
    RUSSIAN(R.string.keyboard_ru),
    CZECH(R.string.keyboard_cs),
    POLISH(R.string.keyboard_pl),
    PORTUGUESE(R.string.keyboard_pt),
    BRAZILIAN(R.string.keyboard_br),
    GREEK(R.string.keyboard_gr),
    TURKISH(R.string.keyboard_tr)
}