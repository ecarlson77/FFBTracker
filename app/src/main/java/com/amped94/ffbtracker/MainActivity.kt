package com.amped94.ffbtracker

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.*
import com.amped94.ffbtracker.data.model.ui.Screen
import com.amped94.ffbtracker.data.model.viewModel.MainViewModel
import com.amped94.ffbtracker.ui.composable.*
import com.amped94.ffbtracker.ui.theme.FFBTrackerTheme
import com.amped94.ffbtracker.util.screenIsShowing

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.fitsSystemWindows = true

        setContent {
            FFBTrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Main()
                }
            }
        }
    }

    @Composable
    fun Main() {
        val navController = rememberNavController()
        val viewModel by remember { mutableStateOf(MainViewModel()) }
        val currentBackstack by navController.currentBackStackEntryAsState()

        Scaffold(
            topBar = {
                TopAppBar(title = { Text("FFBTracker") })
            },
            bottomBar = {
                BottomBar(navController = navController)
            },
            floatingActionButton = {
                if (currentBackstack?.screenIsShowing(Screen.Leagues.View) == true) {
                    FloatingActionButton(onClick = {
                        navController.navigate(Screen.Leagues.Add.route) {
                            restoreState = true
                        }
                    }) {
                        Icon(Icons.Filled.Add, "Add League")
                    }
                }
            },
        ) { innerPadding ->
            NavHost(navController = navController, startDestination = Screen.Players.route, modifier = Modifier.padding(innerPadding)) {
                composable(Screen.Account.route) {
                    Account()
                }
                composable(Screen.Players.route) {
                    PlayersList(viewModel)
                }
                navigation(
                    startDestination = Screen.Leagues.View.route,
                    route = Screen.Leagues.route
                ) {
                    composable(Screen.Leagues.View.route) {
                        Leagues()
                    }
                    navigation(
                        startDestination = Screen.Leagues.Add.LeagueSpecs.route,
                        route = Screen.Leagues.Add.route
                    ) {
                        composable(Screen.Leagues.Add.LeagueSpecs.route) {
                            LeagueSpecs(navController)
                        }
                        composable(Screen.Leagues.Add.AddPlayersToLeague.route) {
                            AddPlayersToLeague(navController)
                        }
                    }

                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        FFBTrackerTheme {
            Text("Test")
        }
    }
}