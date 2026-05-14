package com.developer.raitha_varta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.developer.raitha_varta.RaithaVartaApp
import com.developer.raitha_varta.presentation.screens.homescreen.DailyTipPagerScreen
import com.developer.raitha_varta.presentation.screens.homescreen.HomeScreen
import com.developer.raitha_varta.presentation.screens.languageselectionscreen.LanguageSelectionScreen
import com.developer.raitha_varta.presentation.screens.loginscreen.LoginScreen
import com.developer.raitha_varta.presentation.screens.otpscreen.OtpVerifyScreen
import com.developer.raitha_varta.presentation.screens.splashscreen.SplashScreen
import com.developer.raitha_varta.viewmodel.HomeViewModel
import com.developer.raitha_varta.viewmodel.HomeViewModelFactory
@Composable
fun NavGraph(){
    val navController= rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.SplashScreen
    ) {
        composable<Routes.SplashScreen> {
            SplashScreen(navController)
        }

        composable<Routes.LanguageSelectionScreen>{
            LanguageSelectionScreen(onLanguageSelected = {
                navController.navigate(Routes.LoginScreen)
            })
        }
        composable<Routes.LoginScreen> {
            LoginScreen(navController)
        }
        composable<Routes.OtpVerifyScreen> {backStackEntry->
            val otpRoute: Routes.OtpVerifyScreen = backStackEntry.toRoute()
            OtpVerifyScreen(navController = navController, phoneNumber = otpRoute.phoneNumber)
        }
        composable<Routes.HomeScreen> {
            HomeScreen()
        }
        composable<Routes.DailyTipScreen> {
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    (LocalContext.current.applicationContext as RaithaVartaApp).repository
                )
            )
            val tips by viewModel.tips.collectAsState()
            DailyTipPagerScreen(tips = tips)
        }
    }
}
