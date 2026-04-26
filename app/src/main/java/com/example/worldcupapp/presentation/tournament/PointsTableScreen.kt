package com.example.worldcupapp.presentation.tournament

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun PointsTableScreen(
    viewModel: PointsTableViewModel = hiltViewModel()
) {
    val tableData = viewModel.pointsTable.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp)
    ) {
        Text(
            text = "Points Table",
            color = Color.White,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1E1E1E))
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Team", color = Color.Gray, modifier = Modifier.weight(3f))
            Text("P", color = Color.Gray, modifier = Modifier.weight(1f))
            Text("W", color = Color.Gray, modifier = Modifier.weight(1f))
            Text("Pts", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            Text("NRR", color = Color.Gray, modifier = Modifier.weight(1.5f))
        }

        LazyColumn {
            items(tableData.value) { entry ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 12.dp, horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(entry.team.name, color = Color.White, modifier = Modifier.weight(3f))
                    Text(entry.played.toString(), color = Color.White, modifier = Modifier.weight(1f))
                    Text(entry.won.toString(), color = Color.White, modifier = Modifier.weight(1f))
                    Text(entry.points.toString(), color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                    Text(entry.nrr.toString(), color = Color.White, modifier = Modifier.weight(1.5f))
                }
                Spacer(modifier = Modifier.height(1.dp).fillMaxWidth().background(Color(0xFF333333)))
            }
        }
    }
}
