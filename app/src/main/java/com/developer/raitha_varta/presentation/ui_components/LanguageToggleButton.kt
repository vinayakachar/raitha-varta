package com.developer.raitha_varta.presentation.ui_components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.raitha_varta.ui.theme.ForestGreen

@Composable
fun LanguageToggleButton(
    currentLanguage: String, // Accepts "kn" or "en" from your locale logic
    onLanguageChange: (String) -> Unit
) {
    val isKannada = currentLanguage == "kn"

    // Outer Capsule (Matches image_8413a1.png)
    Box(
        modifier = Modifier
            .width(130.dp)
            .height(38.dp)
            .clip(RoundedCornerShape(19.dp))
            .background(Color.White.copy(alpha = 0.2f)) // Glass effect for the green header
            .clickable {
                val nextLanguage = if (isKannada) "en" else "kn"
                onLanguageChange(nextLanguage)
            }
    ) {
        // Sliding White "Thumb"
        val xOffset by animateDpAsState(targetValue = if (isKannada) 2.dp else 66.dp)

        Box(
            modifier = Modifier
                .offset(x = xOffset, y = 2.dp)
                .width(62.dp)
                .height(34.dp)
                .clip(RoundedCornerShape(17.dp))
                .background(Color.White)
        )

        // Labels
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = "ಕನ್ನಡ",
                    color = if (isKannada) ForestGreen else Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                Text(
                    text = "English",
                    color = if (!isKannada) ForestGreen else Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}