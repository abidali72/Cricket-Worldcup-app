package com.example.worldcupapp.presentation.match

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.worldcupapp.presentation.theme.CricketGreen
import com.example.worldcupapp.presentation.theme.NavySecondary

@Composable
fun ScorecardScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        item {
            ScoreHeader(teamName = "India", score = "245/7", overs = "50.0")
        }
        item {
            BattingTable()
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
            ScoreHeader(teamName = "Australia", score = "112/2", overs = "18.3")
        }
        item {
            BowlingTable()
        }
    }
}

@Composable
fun ScoreHeader(teamName: String, score: String, overs: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(teamName, style = MaterialTheme.typography.titleLarge, color = Color.White)
        Column(horizontalAlignment = Alignment.End) {
            Text(score, style = MaterialTheme.typography.titleLarge, color = Color.White, fontWeight = FontWeight.Bold)
            Text("($overs Overs)", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        }
    }
}

@Composable
fun BattingTable() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavySecondary, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
        // Header
        Row(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            Text("Batter", Modifier.weight(3f), color = Color.Gray)
            Text("R", Modifier.weight(1f), color = Color.Gray)
            Text("B", Modifier.weight(1f), color = Color.Gray)
            Text("4s", Modifier.weight(1f), color = Color.Gray)
            Text("6s", Modifier.weight(1f), color = Color.Gray)
            Text("SR", Modifier.weight(1.5f), color = Color.Gray)
        }
        Divider(color = Color.Gray.copy(alpha = 0.2f))
        
        // Mock Rows
        val batters = listOf(
            BattingStat("Virat Kohli", 85, 110, 6, 0, 77.27),
            BattingStat("KL Rahul", 66, 107, 1, 0, 61.68),
            BattingStat("Rohit Sharma", 47, 31, 4, 3, 151.61)
        )

        batters.forEach { 
            BattingRow(it)
            Divider(color = Color.Gray.copy(alpha = 0.1f))
        }
    }
}

data class BattingStat(val name: String, val runs: Int, val balls: Int, val fours: Int, val sixes: Int, val sr: Double)

@Composable
fun BattingRow(stat: BattingStat) {
    Row(Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Text(stat.name, Modifier.weight(3f), color = Color.White, fontWeight = FontWeight.Medium)
        Text(stat.runs.toString(), Modifier.weight(1f), color = Color.White, fontWeight = FontWeight.Bold)
        Text(stat.balls.toString(), Modifier.weight(1f), color = Color.White)
        Text(stat.fours.toString(), Modifier.weight(1f), color = Color.White)
        Text(stat.sixes.toString(), Modifier.weight(1f), color = Color.White)
        Text(stat.sr.toString(), Modifier.weight(1.5f), color = Color.White)
    }
}

@Composable
fun BowlingTable() {
     Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(NavySecondary, RoundedCornerShape(8.dp))
            .padding(8.dp)
    ) {
         // Header
        Row(Modifier.fillMaxWidth().padding(bottom = 8.dp)) {
            Text("Bowler", Modifier.weight(3f), color = Color.Gray)
            Text("O", Modifier.weight(1f), color = Color.Gray)
            Text("M", Modifier.weight(1f), color = Color.Gray)
            Text("R", Modifier.weight(1f), color = Color.Gray)
            Text("W", Modifier.weight(1f), color = Color.Gray)
            Text("ECO", Modifier.weight(1.5f), color = Color.Gray)
        }
        Divider(color = Color.Gray.copy(alpha = 0.2f))

        // Mock Rows
        val bowlers = listOf(
             BowlingStat("M. Starc", 10.0, 0, 55, 3, 5.50),
             BowlingStat("P. Cummins", 10.0, 0, 34, 2, 3.40)
        )

        bowlers.forEach {
            BowlingRow(it)
            Divider(color = Color.Gray.copy(alpha = 0.1f))
        }
    }
}

data class BowlingStat(val name: String, val overs: Double, val maidens: Int, val runs: Int, val wickets: Int, val eco: Double)

@Composable
fun BowlingRow(stat: BowlingStat) {
    Row(Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        Text(stat.name, Modifier.weight(3f), color = Color.White, fontWeight = FontWeight.Medium)
        Text(stat.overs.toString(), Modifier.weight(1f), color = Color.White)
        Text(stat.maidens.toString(), Modifier.weight(1f), color = Color.White)
        Text(stat.runs.toString(), Modifier.weight(1f), color = Color.White)
        Text(stat.wickets.toString(), Modifier.weight(1f), color = Color.White, fontWeight = FontWeight.Bold)
        Text(stat.eco.toString(), Modifier.weight(1.5f), color = Color.White)
    }
}
