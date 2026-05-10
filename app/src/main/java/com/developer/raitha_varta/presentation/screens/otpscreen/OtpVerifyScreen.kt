package com.developer.raitha_varta.presentation.screens.otpscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.developer.raitha_varta.R
import com.developer.raitha_varta.presentation.screens.loginscreen.OtpCard
import com.developer.raitha_varta.ui.theme.ForestGreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OtpScreen() {
    var otpCode by remember { mutableStateOf("") }

   Column(
       modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 80.dp),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Surface(
           modifier = Modifier.size(100.dp),
           color = ForestGreen,
           shape = RoundedCornerShape(24.dp),
           shadowElevation = 4.dp
       ) {
           Box(
               contentAlignment = Alignment.Center
           ) {
               Icon(
                   painter = painterResource(id = R.drawable.ic_sprout),
                   contentDescription = "Raitha Varta Logo",
                   tint = Color.White,
                   modifier = Modifier.size(60.dp)
               )
           }
       }

       Spacer(modifier = Modifier.height(24.dp))

       Text(
           text="RAITHA-VARTA",
           style = MaterialTheme.typography.headlineLarge,
           fontWeight = FontWeight.Bold,
           color=Color.DarkGray
       )

       Spacer(modifier = Modifier.height(8.dp))

       Text(
           text = "ಕೃಷಿ ತಜ್ಞ ಸಲಹೆಗಾರ",
           fontWeight = FontWeight.SemiBold,
           color = ForestGreen

       )

       Spacer(modifier = Modifier.height(80.dp))

       OtpVerifyCard()


   }
}