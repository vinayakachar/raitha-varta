package com.developer.raitha_varta.presentation.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

fun updateAppLanguage(context: Context, langCode: String) {
    // 1. Set the system-wide locale
    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(langCode)
    AppCompatDelegate.setApplicationLocales(appLocale)

    // 2. Explicitly save to SharedPreferences so the ViewModel remains in sync
    val sharedPrefs = context.getSharedPreferences("RaithaVartaPrefs", Context.MODE_PRIVATE)
    sharedPrefs.edit().putString("selected_language", langCode).apply()
}