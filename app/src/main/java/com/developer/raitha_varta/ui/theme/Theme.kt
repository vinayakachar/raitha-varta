package com.developer.raitha_varta.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = ForestGreen,
    secondary = EmeraldAction,
    tertiary = DeepPlantGreen,
    background = Color(0xFF121212), // Standard dark background
    surface = Color(0xFF121212),
    onPrimary = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = ForestGreen,      // Header color
    secondary = EmeraldAction,  // Actionable tip background
    tertiary = DeepPlantGreen,   // Bold title text
    background = OffWhite,      // Main app surface
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onBackground = DeepPlantGreen,
    onSurface = DeepPlantGreen
)

@Composable
fun RaithavartaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Set dynamicColor to FALSE to keep your Forest Green branding
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // This makes the status bar color match your Forest Green header
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography, // Ensure you have a Typography.kt file
        content = content
    )
}