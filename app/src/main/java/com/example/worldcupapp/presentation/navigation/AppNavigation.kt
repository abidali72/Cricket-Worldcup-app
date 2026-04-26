package com.example.worldcupapp.presentation.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.worldcupapp.presentation.home.HomeScreen
import com.example.worldcupapp.presentation.settings.SettingsScreen
import com.example.worldcupapp.presentation.team.PlayerListScreen
import com.example.worldcupapp.presentation.settings.SettingsScreen
import com.example.worldcupapp.presentation.team.PlayerListScreen
import com.example.worldcupapp.presentation.match.LiveMatchScreen
import com.example.worldcupapp.presentation.tournament.PointsTableScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            // Hide bottom bar on Settings screen (optional, but typical)
            if (currentRoute != Screen.Settings.route) {
                BottomNavigationBar(
                    currentRoute = currentRoute,
                    onNavigate = { route ->
                        navController.navigate(route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) { 
                HomeScreen(
                    onNavigateToLive = {
                        navController.navigate(Screen.LiveStream.route)
                    },
                    onNavigateToSettings = {
                        navController.navigate(Screen.Settings.route)
                    }
                ) 
            }
            composable(Screen.LiveStream.route) { LiveMatchScreen() }
            composable(Screen.PointsTable.route) { PointsTableScreen() }
            composable(Screen.Settings.route) { 
                SettingsScreen(
                    onNavigateBack = { navController.popBackStack() },
                    onNavigateToPlayers = { navController.navigate(Screen.PlayerList.route) }
                ) 
            }
            composable(Screen.PlayerList.route) {
                PlayerListScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}
