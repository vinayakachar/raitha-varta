package com.developer.raitha_varta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.developer.raitha_varta.presentation.screens.homescreen.DailyTipPagerScreen
import com.developer.raitha_varta.presentation.screens.homescreen.HomeScreen
import com.developer.raitha_varta.presentation.screens.loginscreen.LoginScreen
import com.developer.raitha_varta.presentation.screens.otpscreen.OtpScreen
import com.developer.raitha_varta.presentation.screens.splashscreen.SplashScreen

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
        composable<Routes.LoginScreen> {
            LoginScreen()
        }
        composable<Routes.OtpScreen> {
            OtpScreen()
        }
        composable<Routes.HomeScreen> {
            HomeScreen()
        }
        composable<Routes.DailyTipScreen> {
            DailyTipPagerScreen()
        }
    }
}
