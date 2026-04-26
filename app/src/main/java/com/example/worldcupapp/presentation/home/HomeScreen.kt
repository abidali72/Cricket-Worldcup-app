package com.example.worldcupapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.worldcupapp.domain.model.Match
import com.example.worldcupapp.domain.model.MatchStatus
import com.example.worldcupapp.presentation.theme.CricketGreen
import com.example.worldcupapp.presentation.theme.NavyPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToLive: () -> Unit,
    onNavigateToSettings: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val matches = viewModel.matches.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "WORLD CUP LIVE", 
                        style = MaterialTheme.typography.displaySmall,
                        color = Color.White
                    ) 
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = NavyPrimary,
                    scrolledContainerColor = NavyPrimary
                ),
                actions = {
                    IconButton(onClick = onNavigateToSettings) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        containerColor = NavyPrimary
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            // Featured Section
            item {
                Text(
                    text = "FEATURED MATCHES",
                    style = MaterialTheme.typography.titleMedium,
                    color = CricketGreen,
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp, bottom = 8.dp)
                )
            }

            item {
                val featuredMatches = matches.value.take(5) // Take top 5 for slider
                FeaturedSlider(
                    matches = featuredMatches,
                    onMatchClick = { match ->
                        if (match.status == MatchStatus.LIVE) {
                            onNavigateToLive()
                        } else {
                            // TODO: Navigate to Details
                        }
                    }
                )
            }

            // All Matches Section
            item {
                Text(
                    text = "LATEST UPDATES",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, bottom = 8.dp)
                )
            }

            items(matches.value) { match ->
                MatchCard(
                    match = match, 
                    onClick = {
                        if (match.status == MatchStatus.LIVE) {
                            onNavigateToLive()
                        } else {
                            // TODO: Navigate to Match Details
                        }
                    }
                )
            }
        }
    }
}
