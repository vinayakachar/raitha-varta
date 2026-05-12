package com.developer.raitha_varta.presentation.screens.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.presentation.ui_components.HomeScreenBottomNav

@Composable
fun HomeScreen() {
    var currentTab: Routes by remember { mutableStateOf(Routes.HomeScreen) }
    Scaffold(
        topBar = {
            HomeScreenHeader()
        },
        bottomBar = {
            HomeScreenBottomNav(
                currentRoute=currentTab,
                onNavigate={currentTab=it}
            )
        },
        containerColor = Color(0xFFF1FDF6)
    ) {innerPadding->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                is Routes.HomeScreen -> DailyTipPagerScreen()
                is Routes.Success -> Text("Success Stories Screen")
                is Routes.ExpertScreen -> Text("Experts Screen")
                else -> {}
            }
        }
    }
}