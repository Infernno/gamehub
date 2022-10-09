package io.gamehub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.gamehub.core.navigation.NavigationDestination
import io.gamehub.feature.gamedetails.navigation.GameDetailsNavigationDestination
import io.gamehub.feature.gamedetails.navigation.gameDetailsGraph
import io.gamehub.feature.home.navigation.HomeNavigationDestination
import io.gamehub.feature.home.navigation.homeGraph

@Composable
fun HubNavHost(
    navController: NavHostController,
    onNavigateToDestination: (NavigationDestination, String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    startDestination: String = HomeNavigationDestination.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeGraph(
            navigateToDetails = { slug ->
                onNavigateToDestination(
                    GameDetailsNavigationDestination,
                    GameDetailsNavigationDestination.createNavigationRoute(slug)
                )
            }
        ) {
            gameDetailsGraph()
        }
    }
}
