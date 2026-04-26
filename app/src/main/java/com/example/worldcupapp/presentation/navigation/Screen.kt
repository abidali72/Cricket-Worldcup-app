package com.example.worldcupapp.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home", "Matches", Icons.Default.Home)
    object LiveStream : Screen("live_stream", "Live", Icons.Default.PlayArrow)
    object PointsTable : Screen("points_table", "Table", Icons.Default.List)
    object Settings : Screen("settings", "Settings", Icons.Default.Settings)
    object PlayerList : Screen("player_list", "Squad", Icons.Default.Person)
}
