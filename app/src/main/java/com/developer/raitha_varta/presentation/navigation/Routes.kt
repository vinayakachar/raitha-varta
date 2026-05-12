package com.developer.raitha_varta.presentation.navigation

import kotlinx.serialization.Serializable


sealed class Routes {

    @Serializable
    object SplashScreen: Routes()
    @Serializable
    object LanguageSelectionScreen: Routes()
    @Serializable
    object LoginScreen: Routes()
    @Serializable
    object OtpScreen: Routes()
    @Serializable
    object HomeScreen: Routes()
    @Serializable
    object DailyTipScreen: Routes()

    @Serializable
    object Success:Routes()

    @Serializable
    object ExpertScreen:Routes()
}