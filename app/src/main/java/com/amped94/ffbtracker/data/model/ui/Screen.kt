package com.amped94.ffbtracker.data.model.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val img: ImageVector? = null, val title: String? = null) {
    object Account : Screen(
        route = "account",
        img = Icons.Filled.AccountCircle,
        title = "Account"
    )

    object Players : Screen(
        route = "players",
        img = Icons.Filled.List,
        title = "Players"
    )

    object Leagues : Screen(
        route = "leagues",
        img = Icons.Filled.Edit,
        title = "Leagues"
    ) {
        object View : Screen(
            route = "leagues/view",
            img = Icons.Filled.Edit,
            title = "Leagues"
        )

        object Add : Screen(
            route = "leagues/add"
        ) {
            object Create : Screen(
                route = "leagues/add/create"
            )

            object LeagueSpecs : Screen(
                route = "leagues/add/specs"
            )

            object AddPlayersToLeague : Screen(
                route = "leagues/add/players"
            )
        }

        object Edit : Screen(
            route = "leagues/edit"
        )
    }

}