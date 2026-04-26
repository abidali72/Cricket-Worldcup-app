package com.example.worldcupapp.presentation.theme

import androidx.compose.ui.graphics.Color

// Primary Brand Colors
val NavyPrimary = Color(0xFF0F172A) // Deep background
val NavySecondary = Color(0xFF1E293B) // Card background
val NavyTertiary = Color(0xFF334155) // Lighter elements

// Accents
val CricketGreen = Color(0xFF3DDC84) // Android/Cricket Green
val LiveRed = Color(0xFFEF4444) // Live Indicator
val TextWhite = Color(0xFFF8FAFC) // Primary Text
val TextGray = Color(0xFF94A3B8) // Secondary Text

// Gradient Colors
val GradientStart = Color(0xFF0F172A)
val GradientEnd = Color(0xFF020617)

val DarkColorScheme = androidx.compose.material3.darkColorScheme(
    primary = CricketGreen,
    onPrimary = NavyPrimary,
    secondary = LiveRed,
    onSecondary = TextWhite,
    background = NavyPrimary,
    onBackground = TextWhite,
    surface = NavySecondary,
    onSurface = TextWhite,
    surfaceVariant = NavyTertiary,
    onSurfaceVariant = TextGray
)
