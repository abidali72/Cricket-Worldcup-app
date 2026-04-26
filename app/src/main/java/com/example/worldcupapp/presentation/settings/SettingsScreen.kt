package com.example.worldcupapp.presentation.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.worldcupapp.presentation.theme.CricketGreen
import com.example.worldcupapp.presentation.theme.NavyPrimary
import com.example.worldcupapp.presentation.theme.NavySecondary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onNavigateBack: () -> Unit,
    onNavigateToPlayers: () -> Unit
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(true) } // Default to true as per theme

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Settings",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    ) 
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyPrimary
                )
            )
        },
        containerColor = NavyPrimary
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            item {
                SettingsSection("General") {
                    SettingsToggleItem(
                        title = "Live Notifications",
                        subtitle = "Get alerts for wickets and boundaries",
                        icon = Icons.Default.Notifications,
                        checked = notificationsEnabled,
                        onCheckedChange = { notificationsEnabled = it }
                    )
                    Divider(color = NavySecondary)
                    SettingsToggleItem(
                        title = "Dark Mode",
                        subtitle = "Use dark theme for better viewing",
                        icon = Icons.Default.Star, // Using Star as placeholder for simple toggle icon if Moon unavailable
                        checked = darkModeEnabled,
                        onCheckedChange = { darkModeEnabled = it }
                    )
                }
            }

            item {
                SettingsSection("Preferences") {
                    SettingsNavigationItem(
                        title = "Favorite Team",
                        subtitle = "Select your team for personalized updates",
                        icon = Icons.Default.Star,
                        onClick = { onNavigateToPlayers() }
                    )
                    Divider(color = NavySecondary)
                    SettingsNavigationItem(
                        title = "Video Quality",
                        subtitle = "Auto (Recommended)",
                        icon = Icons.Default.Star, // Placeholder
                        onClick = { /* TODO: Open Quality Selector */ }
                    )
                }
            }

            item {
                SettingsSection("Data") {
                    SettingsNavigationItem(
                        title = "Clear Cache",
                        subtitle = "Free up storage space",
                        icon = Icons.Default.Delete,
                        onClick = { /* TODO: Clear Cache */ },
                        textColor = Color.Red
                    )
                }
            }
        }
    }
}

@Composable
fun SettingsSection(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelMedium,
            color = CricketGreen,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(NavySecondary),
            content = content
        )
    }
}

@Composable
fun SettingsToggleItem(
    title: String,
    subtitle: String? = null,
    icon: ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.bodyLarge, color = Color.White)
                if (subtitle != null) {
                    Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
        }
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = CricketGreen
            )
        )
    }
}

@Composable
fun SettingsNavigationItem(
    title: String,
    subtitle: String? = null,
    icon: ImageVector,
    onClick: () -> Unit,
    textColor: Color = Color.White
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = title, style = MaterialTheme.typography.bodyLarge, color = textColor)
                if (subtitle != null) {
                    Text(text = subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                }
            }
        }
        Icon(
            imageVector = Icons.Default.ArrowForward,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}
