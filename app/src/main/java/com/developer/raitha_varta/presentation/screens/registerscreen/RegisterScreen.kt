package com.developer.raitha_varta.presentation.screens.registerscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(onRegistrationComplete: (String, List<String>) -> Unit = { _, _ -> }) {
    var name by remember { mutableStateOf("") }
    var selectedDistrict by remember { mutableStateOf("") }
    val selectedCrops = remember { mutableStateListOf<String>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9FFF9))
            .padding(horizontal = 24.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "ಖಾತೆ ರಚಿಸಿ",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1B5E20)
            )
        )
        Text(
            text = "Complete your profile to get local advisories",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))

        ModernInputField(
            value = name,
            onValueChange = { name = it },
            label = "ಪೂರ್ಣ ಹೆಸರು (Full Name)",
            icon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Stub for DistrictSelector if not found
        Text("ಜಿಲ್ಲೆಯನ್ನು ಆರಿಸಿ (Select District)", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = selectedDistrict,
            onValueChange = { selectedDistrict = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("ಜಿಲ್ಲೆ (District)") },
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "ನಿಮ್ಮ ಬೆಳೆಗಳನ್ನು ಆರಿಸಿ (Select Your Crops)",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        CropChipGroup(selectedCrops)

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onRegistrationComplete(name, selectedCrops.toList()) },
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(bottom = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)),
            shape = RoundedCornerShape(16.dp),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
        ) {
            Text("ಪ್ರಾರಂಭಿಸಿ (Get Started)", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun ModernInputField(value: String, onValueChange: (String) -> Unit, label: String, icon: ImageVector) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            leadingIcon = { Icon(icon, contentDescription = null, tint = Color(0xFF1B5E20)) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1B5E20),
                unfocusedBorderColor = Color.Transparent,
                focusedLabelColor = Color(0xFF1B5E20)
            ),
            singleLine = true
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CropChipGroup(selectedCrops: SnapshotStateList<String>) {
    val cropList = listOf("ಭತ್ತ (Paddy)", "ಅಡಿಕೆ (Areca nut)", "ತೆಂಗು (Coconut)", "ಟೊಮೆಟೊ (Tomato)")

    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        cropList.forEach { crop ->
            val isSelected = selectedCrops.contains(crop)
            FilterChip(
                selected = isSelected,
                onClick = {
                    if (isSelected) selectedCrops.remove(crop) else selectedCrops.add(crop)
                },
                label = { Text(crop, modifier = Modifier.padding(8.dp)) },
                shape = RoundedCornerShape(12.dp),
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = Color(0xFF1B5E20),
                    selectedLabelColor = Color.White,
                    containerColor = Color.White
                ),
                border = FilterChipDefaults.filterChipBorder(
                    borderColor = if (isSelected) Color.Transparent else Color.LightGray,
                    enabled = true,
                    selected = isSelected
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview() {
    RegisterScreen()
}
