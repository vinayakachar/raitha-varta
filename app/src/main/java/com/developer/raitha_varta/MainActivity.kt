package com.developer.raitha_varta

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.developer.raitha_varta.presentation.navigation.NavGraph
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.ui.theme.RaithavartaTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // 1. Check if a user session already exists
        val currentUser = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser

        // 2. Decide the starting point
        // If user exists, go to Home; if not, show the Splash/Login flow
        val startRoute: Any = if (currentUser != null) {
            Routes.HomeScreen
        } else {
            Routes.SplashScreen
        }

        setContent {
            RaithavartaTheme {
                // 3. Pass the dynamic start route to your NavGraph
                NavGraph(startDestination = startRoute)
            }
        }
    }
}