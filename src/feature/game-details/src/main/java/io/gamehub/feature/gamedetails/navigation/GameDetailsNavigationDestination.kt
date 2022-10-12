package io.gamehub.feature.gamedetails.navigation

import android.net.Uri
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.gamehub.core.navigation.NavigationDestination
import io.gamehub.feature.gamedetails.GameDetailsScreen

object GameDetailsNavigationDestination : NavigationDestination {
    private const val Route = "game_details_route"
    const val ArgumentKey = "game_details_slug_arg"

    override val route: String = "$Route/{$ArgumentKey}"
    override val destination: String = "game_details_destination"

    fun createNavigationRoute(slug: String): String {
        val encodedId = Uri.encode(slug)
        return "$Route/$encodedId"
    }
}

fun NavGraphBuilder.gameDetailsGraph(
    goBack: () -> Unit
) {
    composable(
        route = GameDetailsNavigationDestination.route,
        arguments = listOf(
            navArgument(GameDetailsNavigationDestination.ArgumentKey) { type = NavType.StringType }
        )
    ) {
        GameDetailsScreen(
            goBack = goBack
        )
    }
}
