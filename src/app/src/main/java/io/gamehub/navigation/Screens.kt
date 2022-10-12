package io.gamehub.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import io.gamehub.R
import io.gamehub.feature.gamedetails.DETAILS_ARG_KEY

enum class RootScreen(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector,
) {
    Home("home", R.string.menu_home, Icons.Default.Home),
    UpcomingGames("upcoming", R.string.menu_upcoming, Icons.Default.CalendarMonth)
}

sealed class Screen(
    private val route: String,
) {
    fun createRoute(root: RootScreen) = "${root.route}/$route"

    object Home : Screen("home")
    object UpcomingGames : Screen("upcoming")

    object GameDetails : Screen("details/{$DETAILS_ARG_KEY}") {
        fun createRoute(root: RootScreen, slug: String): String {
            return "${root.route}/details/$slug"
        }
    }
}
