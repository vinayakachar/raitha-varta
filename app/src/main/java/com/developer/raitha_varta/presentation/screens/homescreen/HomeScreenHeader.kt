package com.developer.raitha_varta.presentation.screens.homescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.developer.raitha_varta.R
import com.developer.raitha_varta.ui.theme.ForestGreen

@Composable
fun HomeScreenHeader(
    selectedCategoryId: String,
    onCategorySelected: (String) -> Unit
) {
    val categories = listOf(
        CropCategory("all", R.string.cat_all),
        CropCategory("paddy", R.string.cat_paddy),
        CropCategory("coconut", R.string.cat_coconut),
        CropCategory("arecanut", R.string.cat_arecanut),
        CropCategory("tomato", R.string.cat_tomato)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(ForestGreen)
            .padding(top = 50.dp, bottom = 12.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_sprout),
                contentDescription = "Raitha Varta Logo",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = stringResource(R.string.app_name).uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    text = stringResource(R.string.tagline),
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = selectedCategoryId == category.id
                val unselectedChipColor = Color.White.copy(alpha = 0.15f)
                val unselectedTextColor = Color.White
                FilterChip(
                    selected = selectedCategoryId == category.id,
                    onClick = { onCategorySelected(category.id) },
                    label = {
                        Text(
                            text = stringResource(id = category.nameRes),
                            style = MaterialTheme.typography.labelLarge
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = Color.White,
                        selectedLabelColor = ForestGreen,

                        containerColor = unselectedChipColor,
                        labelColor = unselectedTextColor

                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = isSelected,
                        borderColor = Color.White.copy(alpha = 0.3f),
                        selectedBorderColor = Color.Transparent,
                        borderWidth = 1.dp
                    )
                )
            }
        }
    }
}

data class CropCategory(
    val id: String,
    val nameRes: Int
)
