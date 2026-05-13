package com.developer.raitha_varta.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.developer.raitha_varta.presentation.screens.homescreen.DailyTipPagerScreen
import com.developer.raitha_varta.presentation.screens.homescreen.HomeScreen
import com.developer.raitha_varta.presentation.screens.languageselectionscreen.LanguageSelectionScreen
import com.developer.raitha_varta.presentation.screens.loginscreen.LoginScreen
import com.developer.raitha_varta.presentation.screens.otpscreen.OtpVerifyScreen
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

        composable<Routes.LanguageSelectionScreen>{
            LanguageSelectionScreen(onLanguageSelected = { lang ->
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
            DailyTipPagerScreen()
        }
    }
}
