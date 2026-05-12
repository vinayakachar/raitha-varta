package com.developer.raitha_varta.presentation.screens.splashscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.developer.raitha_varta.R
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.ui.theme.ForestGreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavHostController) {

    LaunchedEffect(key1 = true){
        delay(2000L)
        navController.navigate(Routes.HomeScreen){
            popUpTo(Routes.SplashScreen) { inclusive = true }
        }
    }
    Box(modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally)  {
            Surface(
                modifier = Modifier.size(120.dp),
                color= ForestGreen,
                shape = RoundedCornerShape(24.dp)

            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        painter = painterResource(R.drawable.ic_sprout),
                        contentDescription = "Raitha Varta logo",
                        tint = Color.White,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text="RAITHA VARTA",
                style=MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color=Color.DarkGray
            )


            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "ಕೃಷಿ ತಜ್ಞ ಸಲಹೆಗಾರ",
                fontWeight = FontWeight.SemiBold,
                color = ForestGreen

            )
        }
    }
   }