package com.developer.raitha_varta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.developer.raitha_varta.presentation.navigation.NavGraph
import com.developer.raitha_varta.ui.theme.RaithavartaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RaithavartaTheme {
                NavGraph()
            }
        }
    }
}

