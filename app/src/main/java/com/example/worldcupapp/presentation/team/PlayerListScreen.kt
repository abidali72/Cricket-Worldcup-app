package com.example.worldcupapp.presentation.team

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.worldcupapp.presentation.theme.NavyPrimary
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldcupapp.domain.model.Player
import com.example.worldcupapp.domain.repository.PlayerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel() {
    var players by mutableStateOf<List<Player>>(emptyList())
        private set

    init {
        fetchPlayers()
    }

    private fun fetchPlayers() {
        viewModelScope.launch {
            // Fetching for Team ID 2 (as per example) - likely Australia or India
            players = repository.getPlayers(2)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlayerListScreen(
    onNavigateBack: () -> Unit,
    viewModel: PlayerListViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Team Squad", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = NavyPrimary)
            )
        },
        containerColor = NavyPrimary
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
             PlayerListContent(players = viewModel.players)
        }
    }
}

@Composable
fun PlayerListContent(players: List<Player>) {
    if (players.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = Color.White)
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(players) { player ->
                PlayerItem(player)
            }
        }
    }
}

@Composable
fun PlayerItem(player: Player) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = player.imageUrl,
                contentDescription = null,
                modifier = Modifier.size(60.dp).clip(CircleShape).background(Color.Gray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = player.name, style = MaterialTheme.typography.titleMedium, color = Color.White)
                player.role?.let {
                    Text(text = it, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                }
            }
        }
    }
}
