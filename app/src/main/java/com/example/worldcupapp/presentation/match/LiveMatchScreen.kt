package com.example.worldcupapp.presentation.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.worldcupapp.presentation.team.PlayerListScreen
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.example.worldcupapp.presentation.theme.CricketGreen
import com.example.worldcupapp.presentation.theme.NavyPrimary
import com.example.worldcupapp.presentation.video.VideoPlayer
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.worldcupapp.presentation.team.PlayerListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LiveMatchScreen(
    // We can inject shared viewmodel or separate ones
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Info", "Squad", "Scorecard")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("India vs Australia", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyPrimary)
            )
        },
        containerColor = NavyPrimary
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Video Player Section
            VideoPlayer(
                videoUrl = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8",
                modifier = Modifier.fillMaxWidth()
            )

            // Tabs
            TabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = NavyPrimary,
                contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                        color = CricketGreen
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            // Tab Content
            when (selectedTabIndex) {
                0 -> MatchInfoTab()
                1 -> SquadTab()
                2 -> ScorecardScreen()
            }
        }
    }
}

@Composable
fun MatchInfoTab() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Match Info Placeholder", color = Color.White)
        // Add more info like venue, toss, etc.
    }
}

@Composable
fun SquadTab() {
    // Reusing PlayerListScreen logic but stripping Scaffold if possible, 
    // or just embedding it. Since PlayerListScreen has a Scaffold, 
    // it might nest incorrectly. 
    // Ideally werefactor PlayerListScreen to split Content from Screen.
    // For now, let's use a workaround or assuming PlayerListScreen handles basic list.
    // Actually, creating a minimal version here calling the same VM might be cleaner.
    
    val viewModel: PlayerListViewModel = hiltViewModel()
    // We need to trigger fetch if not already. VM init does it.
    
    // We'll reuse the LazyColumn part from PlayerListScreen essentially.
    // But PlayerListScreen has a Scaffold. Let's just create a simplified view here.
    
    com.example.worldcupapp.presentation.team.PlayerListContent(viewModel.players)
} 
