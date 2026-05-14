package com.developer.raitha_varta.presentation.navigation

import androidx.activity.ComponentActivity
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
import com.developer.raitha_varta.viewmodel.AuthViewModel
import com.developer.raitha_varta.viewmodel.HomeViewModel
import com.developer.raitha_varta.viewmodel.HomeViewModelFactory

@Composable
fun NavGraph(startDestination: Any) { // Updated from String to Any for Type-Safety
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // 1. Splash Screen
        composable<Routes.SplashScreen> {
            SplashScreen(navController)
        }

        // 2. Language Selection
        composable<Routes.LanguageSelectionScreen> {
            LanguageSelectionScreen(onLanguageSelected = {
                navController.navigate(Routes.LoginScreen)
            })
        }

        // 3. Login Screen
        composable<Routes.LoginScreen> {
            val authViewModel: AuthViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
            LoginScreen(navController, authViewModel)
        }

        // 4. OTP Verification (Handling Arguments)
        composable<Routes.OtpVerifyScreen> { backStackEntry ->
            // Scope to SAME Activity - now the ID will be there!
            val authViewModel: AuthViewModel = viewModel(viewModelStoreOwner = LocalContext.current as ComponentActivity)
            val phoneNumber = backStackEntry.arguments?.getString("phoneNumber") ?: ""
            OtpVerifyScreen(navController, phoneNumber, authViewModel)
        }

        // 5. Main Home Screen
        composable<Routes.HomeScreen> {
            HomeScreen()
        }

        // 6. Daily Tips (With ViewModel injection)
        composable<Routes.DailyTipScreen> {
            val context = LocalContext.current
            val viewModel: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    (LocalContext.current.applicationContext as RaithaVartaApp).repository,
                    sharedPreferences = context.getSharedPreferences("RaithaVartaPrefs", android.content.Context.MODE_PRIVATE)
                )
            )
            val tips by viewModel.tips.collectAsState()
            DailyTipPagerScreen(tips = tips)
        }
    }
}