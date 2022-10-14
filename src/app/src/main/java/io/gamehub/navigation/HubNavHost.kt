package io.gamehub.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import io.gamehub.feature.filter.FILTER_ARG_KEY
import io.gamehub.feature.filter.FilterListScreen
import io.gamehub.feature.gamedetails.DETAILS_ARG_KEY
import io.gamehub.feature.gamedetails.GameDetailsScreen
import io.gamehub.feature.home.HomeScreen
import io.gamehub.feature.releasecalendar.ReleaseCalendar
import io.gamehub.feature.search.SearchScreen

@Composable
fun HubNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = RootScreen.Home.route,
        modifier = modifier,
    ) {
        addHomeTopLevel(navController)
        addReleaseCalendarTopLevel(navController)
    }
}

private fun NavGraphBuilder.addHomeTopLevel(
    navController: NavController,
) {
    setupTab(
        root = RootScreen.Home,
        startScreen = Screen.Home
    ) { root ->
        addHome(navController, root)
        addSearch(navController, root)
        addFilterScreen(navController, root)
        addGameDetails(navController, root)
    }
}

private fun NavGraphBuilder.addReleaseCalendarTopLevel(
    navController: NavController,
) {
    setupTab(
        root = RootScreen.ReleaseCalendar,
        startScreen = Screen.ReleaseCalendar
    ) { root ->
        addReleaseCalendar(navController, root)
        addGameDetails(navController, root)
    }
}

private fun NavGraphBuilder.setupTab(
    root: RootScreen,
    startScreen: Screen,
    configure: NavGraphBuilder.(RootScreen) -> Unit,
) {
    navigation(
        route = root.route,
        startDestination = startScreen.createRoute(root)
    ) {
        configure(root)
    }
}

private fun NavGraphBuilder.addHome(
    navController: NavController,
    root: RootScreen,
) {
    composable(
        route = Screen.Home.createRoute(root),
    ) {
        HomeScreen(
            navigateToDetails = { slug ->
                navController.navigate(
                    Screen.GameDetails.createRoute(root, slug)
                )
            },
            navigateToSearch = {
                navController.navigate(
                    Screen.Search.createRoute(root)
                )
            },
            navigateToGenre = { key ->
                navController.navigate(
                    Screen.FilterScreen.createRoute(root, key)
                )
            }
        )
    }
}

private fun NavGraphBuilder.addReleaseCalendar(
    navController: NavController,
    root: RootScreen,
) {
    composable(
        route = Screen.ReleaseCalendar.createRoute(root),
    ) {
        ReleaseCalendar(
            navigateToDetails = { slug ->
                navController.navigate(
                    Screen.GameDetails.createRoute(root, slug)
                )
            }
        )
    }
}

private fun NavGraphBuilder.addGameDetails(
    navController: NavController,
    root: RootScreen,
) {
    composable(
        route = Screen.GameDetails.createRoute(root),
        arguments = listOf(
            navArgument(DETAILS_ARG_KEY) { type = NavType.StringType }
        )
    ) {
        GameDetailsScreen(
            goBack = {
                navController.popBackStack()
            }
        )
    }
}

private fun NavGraphBuilder.addSearch(
    navController: NavController,
    root: RootScreen,
) {
    composable(
        route = Screen.Search.createRoute(root)
    ) {
        SearchScreen(
            goBack = {
                navController.popBackStack()
            },
            navigateToDetails = { slug ->
                navController.navigate(
                    Screen.GameDetails.createRoute(root, slug)
                )
            }
        )
    }
}

private fun NavGraphBuilder.addFilterScreen(
    navController: NavController,
    root: RootScreen,
) {
    composable(
        route = Screen.FilterScreen.createRoute(root),
        arguments = listOf(
            navArgument(FILTER_ARG_KEY) { type = NavType.StringType }
        )
    ) {
        FilterListScreen(
            navigateToDetails = { slug ->
                navController.navigate(
                    Screen.GameDetails.createRoute(root, slug)
                )
            },
            goBack = {
                navController.popBackStack()
            }
        )
    }
}
