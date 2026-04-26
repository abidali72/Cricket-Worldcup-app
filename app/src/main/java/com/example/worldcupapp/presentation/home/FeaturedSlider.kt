package com.example.worldcupapp.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.worldcupapp.domain.model.Match
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FeaturedSlider(
    matches: List<Match>,
    onMatchClick: (Match) -> Unit
) {
    if (matches.isEmpty()) {
        // Fallback or Loading State for debugging
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Loading Matches...", color = MaterialTheme.colorScheme.onSurface)
        }
        return
    }

    val pagerState = rememberPagerState(pageCount = { matches.size })
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    // Auto-scroll logic
    LaunchedEffect(isDragged) {
        if (!isDragged) {
            while (true) {
                delay(5000L) // 5 seconds delay
                // Only scroll if we are not at the end, or loop back
                if (matches.isNotEmpty()) {
                    val nextPage = (pagerState.currentPage + 1) % matches.size
                    pagerState.animateScrollToPage(nextPage)
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            contentPadding = PaddingValues(horizontal = 24.dp), // Check next/prev preview
            pageSpacing = 16.dp,
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp) // Taller for featured items
        ) { page ->
            // Parallax or scale effect could go here
            val match = matches[page]
            MatchCard(
                match = match,
                onClick = { onMatchClick(match) },
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Indicators
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            repeat(matches.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) 
                    MaterialTheme.colorScheme.primary 
                else 
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.3f)
                
                androidx.compose.foundation.Canvas(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(8.dp)
                ) {
                    drawCircle(color)
                }
            }
        }
    }
}
