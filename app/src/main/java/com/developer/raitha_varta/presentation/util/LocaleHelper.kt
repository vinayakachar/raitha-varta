package com.developer.raitha_varta.presentation.util

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat

fun updateAppLanguage(context: Context, langCode: String) {
    val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(langCode)

    AppCompatDelegate.setApplicationLocales(appLocale)
}