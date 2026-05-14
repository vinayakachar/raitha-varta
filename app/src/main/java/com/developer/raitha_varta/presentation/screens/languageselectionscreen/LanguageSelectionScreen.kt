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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.raitha_varta.R
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.presentation.util.updateAppLanguage
import com.developer.raitha_varta.ui.theme.ForestGreen

@Composable
fun LanguageSelectionScreen(onLanguageSelected: (String) -> Unit) {
   var currentSelection by remember { mutableStateOf("kn") }
    val context= LocalContext.current
    val scope= rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LanguageLogoHeader()

        Spacer(modifier = Modifier.height(80.dp))

        Text(
            text = "ಭಾಷೆಯನ್ನು ಆರಿಸಿ\nChoose Language",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color = Color.DarkGray,
            lineHeight = 34.sp
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Reusable Button Logic
        LanguageToggleButtons(
            selectedLang = currentSelection,
            onLangChange = { lang ->
                currentSelection = lang
               updateAppLanguage(context,lang)
                onLanguageSelected(lang)
            }
        )
    }

}
