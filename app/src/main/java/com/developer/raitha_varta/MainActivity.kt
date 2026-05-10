package com.developer.raitha_varta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.developer.raitha_varta.presentation.screens.loginscreen.LoginScreen
import com.developer.raitha_varta.presentation.screens.otpscreen.OtpScreen
import com.developer.raitha_varta.ui.theme.RaithavartaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaithavartaTheme {
                OtpScreen()
            }
        }
    }
}

