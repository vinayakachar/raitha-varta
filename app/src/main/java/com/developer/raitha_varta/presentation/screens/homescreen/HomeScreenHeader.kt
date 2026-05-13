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
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenHeader() {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(ForestGreen)
            .padding(top=16.dp, bottom = 12.dp)
    ){
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
            Spacer(modifier = Modifier.width(8.dp))
            Column() {
                Text(
                    stringResource(R.string.app_name).uppercase(),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Text(
                    "Raitha-Varta • Your Farm Advisor",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }
        }

            Spacer(modifier = Modifier.height(16.dp))

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
               item { FilterChip(
                   label = { Text("All") },
                   selected = true,
                   onClick = { /*TODO*/ },
               ) }
                item { FilterChip(
                    label = { Text("Paddy") },
                    selected = true,
                    onClick = { /*TODO*/ },
                ) }
                item { FilterChip(
                    label = { Text("Fruits") },
                    selected = true,
                    onClick = { /*TODO*/ },
                ) }
                item { FilterChip(
                    label = { Text("Coconut") },
                    selected = true,
                    onClick = { /*TODO*/ },
                ) }
                item { FilterChip(
                    label = { Text("Coconut") },
                    selected = true,
                    onClick = { /*TODO*/ },
                ) }
                item { FilterChip(
                    label = { Text("Coconut") },
                    selected = true,
                    onClick = { /*TODO*/ },
                ) }
        }
    }
}

