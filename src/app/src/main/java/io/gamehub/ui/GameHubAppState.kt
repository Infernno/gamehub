package io.gamehub.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.gamehub.R
import io.gamehub.core.navigation.NavigationDestination
import io.gamehub.feature.home.navigation.HomeNavigationDestination
import io.gamehub.feature.releasecalendar.navigation.ReleaseCalendarNavigation
import io.gamehub.navigation.TopLevelDestination

@Composable
fun rememberHubAppState(
    navController: NavHostController = rememberNavController()
): GameHubAppState {
    return remember(navController) {
        GameHubAppState(navController)
    }
}

class GameHubAppState(
    val navController: NavHostController
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    /**
     * Top level destinations to be used in the BottomBar and NavRail
     */
    val topLevelDestinations: List<TopLevelDestination> = listOf(
        TopLevelDestination(
            route = HomeNavigationDestination.route,
            destination = HomeNavigationDestination.destination,
            selectedIcon = Icons.Default.Home,
            label = R.string.menu_home
        ),
        TopLevelDestination(
            route = ReleaseCalendarNavigation.route,
            destination = ReleaseCalendarNavigation.destination,
            selectedIcon = Icons.Default.CalendarMonth,
            label = R.string.menu_upcoming
        ),
    )

    /**
     * UI logic for navigating to a particular destination in the app. The NavigationOptions to
     * navigate with are based on the type of destination, which could be a top level destination or
     * just a regular destination.
     *
     * Top level destinations have only one copy of the destination of the back stack, and save and
     * restore state whenever you navigate to and from it.
     * Regular destinations can have multiple copies in the back stack and state isn't saved nor
     * restored.
     *
     * @param destination: The [NavigationDestination] the app needs to navigate to.
     * @param route: Optional route to navigate to in case the destination contains arguments.
     */
    fun navigate(destination: NavigationDestination, route: String? = null) {
        println("Navigation: $destination, route = $route")
        trace("Navigation: $destination") {
            if (destination is TopLevelDestination) {
                navController.navigate(route ?: destination.route) {
                    // Pop up to the start destination of the graph to
                    // avoid building up a large stack of destinations
                    // on the back stack as users select items
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    // Avoid multiple copies of the same destination when
                    // reselecting the same item
                    launchSingleTop = true
                    // Restore state when reselecting a previously selected item
                    restoreState = true
                }
            } else {
                navController.navigate(route ?: destination.route)
            }
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }
}
