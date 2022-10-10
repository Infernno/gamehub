package io.gamehub.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.gamehub.core.navigation.NavigationDestination
import io.gamehub.feature.search.SearchScreen

object SearchScreenDestination : NavigationDestination {
    override val route: String = "search_route"
    override val destination: String = "search_destination"
}

fun NavGraphBuilder.searchGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = SearchScreenDestination.route,
        startDestination = SearchScreenDestination.destination
    ) {
        composable(route = SearchScreenDestination.destination) {
            SearchScreen()
        }
        nestedGraphs()
    }
}
