package com.developer.raitha_varta.presentation.screens.loginscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.raitha_varta.ui.theme.ForestGreen

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OtpCard() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(48.dp),
        color = Color.White,
        shadowElevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "ಮೊಬೈಲ್ ಮೂಲಕ ಪ್ರವೇಶ",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.DarkGray,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(36.dp))

            Text(
                modifier = Modifier.align(Alignment.Start),
                text="Mobile Number",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray
            )

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("9876543210", color = Color(0xFFA0AEC0)) },
                leadingIcon = { Icon(Icons.Default.Phone, contentDescription = null) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF0FFF4),
                    unfocusedContainerColor = Color(0xFFF0FFF4),
                    unfocusedBorderColor = Color(0xFFC6F6D5),
                    focusedBorderColor = Color(0xFF1B5E20)
                )
                )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "* Don't Add Country Code",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start),
                color = Color(0xFFA0AEC0)
            )

            Spacer(modifier = Modifier.height(42.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth().height(64.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = ForestGreen
                )
                , shape = RoundedCornerShape(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text="ಒಟಿಪಿ ಕಳುಹಿಸಿ",
                        fontSize = 20.sp
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Icon(Icons.Default.ArrowForward, contentDescription = null)
                }
            }
        }
    }
}
