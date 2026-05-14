package com.developer.raitha_varta.presentation.screens.homescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.developer.raitha_varta.RaithaVartaApp
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.presentation.ui_components.HomeScreenBottomNav
import com.developer.raitha_varta.presentation.screens.successscreen.SuccessStoryPagerScreen
import com.developer.raitha_varta.viewmodel.HomeViewModel
import com.developer.raitha_varta.viewmodel.HomeViewModelFactory
import com.developer.raitha_varta.viewmodel.SuccessViewModel

@Composable
fun HomeScreen() {
    val context = LocalContext.current
    val app = context.applicationContext as RaithaVartaApp

    val viewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(app.repository)
    )
    val successViewModel: SuccessViewModel = viewModel(factory = HomeViewModelFactory(app.repository))
    val storyList by successViewModel.stories.collectAsState()

    val tipList by viewModel.tips.collectAsState()
    var currentTab by remember { mutableStateOf<Routes>(Routes.HomeScreen) }

    androidx.compose.runtime.LaunchedEffect(Unit) {
        viewModel.refreshTipsFromAdvisor()
    }

    Scaffold(
        topBar = { HomeScreenHeader(
            selectedCategoryId = viewModel.selectedCategory.value,
            onCategorySelected = { id ->
                viewModel.updateCategory(id)
            }
        ) },
        bottomBar = {
            HomeScreenBottomNav(
                currentRoute = currentTab,
                onNavigate = { currentTab = it }
            )
        },
        containerColor = Color(0xFFF1FDF6)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                is Routes.HomeScreen -> {
                    DailyTipPagerScreen(tips = tipList)
                }
                is Routes.Success -> {
                    SuccessStoryPagerScreen(stories = storyList)
                }
                is Routes.ExpertScreen -> Text("Experts Screen")
                else -> {}
            }
        }
    }
}
