package com.developer.raitha_varta.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.developer.raitha_varta.R

sealed class TabItem(
    val route: Routes,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    object Daily : TabItem(Routes.HomeScreen, R.string.bottom_daily, Icons.Default.Home)
    object Success : TabItem(Routes.Success, R.string.bottom_success, Icons.Default.Star)
    object Experts : TabItem(Routes.ExpertScreen, R.string.bottom_experts, Icons.Default.CameraAlt)
}
