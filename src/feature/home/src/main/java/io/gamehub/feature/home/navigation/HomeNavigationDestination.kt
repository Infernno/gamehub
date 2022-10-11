package io.gamehub.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.gamehub.core.navigation.NavigationDestination
import io.gamehub.feature.home.HomeScreen

object HomeNavigationDestination : NavigationDestination {
    override val route: String = "home_route"
    override val destination: String = "home_destination"
}

fun NavGraphBuilder.homeGraph(
    navigateToDetails: (String) -> Unit,
) {
    navigation(
        route = HomeNavigationDestination.route,
        startDestination = HomeNavigationDestination.destination
    ) {
        composable(route = HomeNavigationDestination.destination) {
            HomeScreen(
                navigateToDetails = navigateToDetails,
            )
        }
    }
}
