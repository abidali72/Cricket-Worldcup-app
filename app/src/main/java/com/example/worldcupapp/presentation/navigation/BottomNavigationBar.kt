package com.example.worldcupapp.presentation.navigation

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        Screen.Home,
        Screen.LiveStream,
        Screen.PointsTable
    )

    NavigationBar(
        containerColor = Color(0xFF1E1E1E), // Dark theme
        contentColor = Color.White
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = { onNavigate(screen.route) }
            )
        }
    }
}
