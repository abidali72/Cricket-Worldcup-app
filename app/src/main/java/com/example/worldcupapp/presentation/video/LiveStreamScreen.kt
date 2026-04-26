package com.example.worldcupapp.presentation.video

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LiveStreamScreen(
    matchTitle: String = "India vs Australia - Live",
    streamUrl: String = "https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8" // Public test stream
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Video Player Area
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            VideoPlayer(videoUrl = streamUrl)
        }

        // Match Info below video
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = matchTitle,
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Live Stream • 1.2M Viewers",
                color = Color.Red,
                fontSize = 14.sp
            )
        }
    }
}
