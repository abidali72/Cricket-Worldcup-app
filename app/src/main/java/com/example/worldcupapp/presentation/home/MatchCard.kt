package com.example.worldcupapp.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.worldcupapp.domain.model.Match
import com.example.worldcupapp.domain.model.MatchScore
import com.example.worldcupapp.domain.model.MatchStatus
import com.example.worldcupapp.presentation.theme.CricketGreen
import com.example.worldcupapp.presentation.theme.LiveRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MatchCard(
    match: Match,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surface,
                            Color(0xFF0F172A) // Darker shade at bottom
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // Header: Status and Venue
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (match.status == MatchStatus.LIVE) {
                        Badge(
                            containerColor = LiveRed,
                            contentColor = Color.White
                        ) {
                            Text(
                                text = "LIVE",
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                            )
                        }
                    } else {
                        Text(
                            text = match.status.name,
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Text(
                        text = match.venue,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        maxLines = 1
                    )
                }
                
                Spacer(modifier = Modifier.height(20.dp))

                // Teams and Scores
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TeamInfo(match.teamA.shortName, match.teamA.flagUrl, match.scoreA, Alignment.Start)
                    
                    Text(
                        text = "VS",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f),
                        fontWeight = FontWeight.Black
                    )
                    
                    TeamInfo(match.teamB.shortName, match.teamB.flagUrl, match.scoreB, Alignment.End)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Summary / Key Event
                match.winningSummary?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = CricketGreen,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}

@Composable
fun TeamInfo(
    name: String, 
    flagUrl: String, 
    score: MatchScore?, 
    alignment: Alignment.Horizontal
) {
    Column(horizontalAlignment = alignment) {
        AsyncImage(
            model = flagUrl,
            contentDescription = name,
            modifier = Modifier
                .size(56.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .border(1.dp, MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.2f), RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = name, 
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface
        )
        
        score?.let {
            Text(
                text = "${it.runs}/${it.wickets}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onSurface,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "(${it.overs})",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
