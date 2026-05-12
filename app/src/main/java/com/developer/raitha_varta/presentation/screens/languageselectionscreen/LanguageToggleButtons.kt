package com.developer.raitha_varta.presentation.screens.languageselectionscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LanguageToggleButtons(
    selectedLang: String,
    onLangChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        val isKannada = selectedLang == "kn"
        Button(
            onClick = { onLangChange("kn") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isKannada) Color(0xFF1B5E20) else Color.Transparent,
                contentColor = if (isKannada) Color.White else Color(0xFF1B5E20)
            ),
            border = if (!isKannada) BorderStroke(2.dp, Color(0xFF1B5E20)) else null,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "ಕನ್ನಡ",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        val isEnglish = selectedLang == "en"
        Button(
            onClick = { onLangChange("en") },
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isEnglish) Color(0xFF1B5E20) else Color.Transparent,
                contentColor = if (isEnglish) Color.White else Color(0xFF1B5E20)
            ),
            border = if (!isEnglish) BorderStroke(2.dp, Color(0xFF1B5E20)) else null,
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "English",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
