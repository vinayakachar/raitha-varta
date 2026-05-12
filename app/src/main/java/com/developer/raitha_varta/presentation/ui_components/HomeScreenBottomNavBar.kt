package com.developer.raitha_varta.presentation.ui_components

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.developer.raitha_varta.presentation.navigation.Routes
import com.developer.raitha_varta.presentation.navigation.TabItem

@Composable
fun HomeScreenBottomNav(currentRoute: Routes,
                        onNavigate: (Routes) -> Unit) {

    val items=listOf(TabItem.Daily,TabItem.Success,TabItem.Experts)

    NavigationBar(
        contentColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier.height(100.dp),
        tonalElevation = 8.dp
    ) {
        items.forEach {item ->
            NavigationBarItem(
                label = {
                    Text(
                        text=item.label,
                        fontWeight = FontWeight.Bold
                    )
                },
                icon={
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label
                    )
                },
                onClick = { onNavigate(item.route) },
                selected = currentRoute == item.route,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1B5E20),
                    selectedTextColor = Color(0xFF1B5E20),
                    indicatorColor = Color(0xFFE8F5E9),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}
