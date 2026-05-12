package com.developer.raitha_varta.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class TabItem(

val route: Routes,    // This is now a Routes object, not a String
val label: String,
val icon: ImageVector
) {
    object Daily : TabItem(Routes.HomeScreen, "ದೈನಿಕ", Icons.Default.Home)
    object Success : TabItem(Routes.Success, "ಯಶಸ್ಸು", Icons.Default.Star)
    object Experts : TabItem(Routes.ExpertScreen, "ತಜ್ಞರು", Icons.Default.Add)
}