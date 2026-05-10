package com.developer.raitha_varta.presentation.screens.languageselectionscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.raitha_varta.R
import com.developer.raitha_varta.ui.theme.ForestGreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LanguageSelectionScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
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
        Spacer(modifier = Modifier.height(48.dp))


        Text(
            text = "ಭಾಷೆಯನ್ನು ಆರಿಸಿ\nChoose Language",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = Color.DarkGray,
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(72.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF1B5E20)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("ಕನ್ನಡ", fontSize = 22.sp, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = {},
            modifier = Modifier.fillMaxWidth().height(72.dp),
            border = BorderStroke(2.dp,Color(0xFF1B5E20)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("English", fontSize = 22.sp, color = Color(0xFF1B5E20),
                fontWeight = FontWeight.Bold)
        }
    }

}