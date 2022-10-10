package io.gamehub.feature.releasecalendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.gamehub.core.navigation.NavigationDestination
import io.gamehub.feature.releasecalendar.ReleaseCalendar

object ReleaseCalendarNavigation : NavigationDestination {
    override val route: String = "release_calendar_route"
    override val destination: String = "release_calendar_destination"
}

fun NavGraphBuilder.releaseCalendarGraph(
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = ReleaseCalendarNavigation.route,
        startDestination = ReleaseCalendarNavigation.destination
    ) {
        composable(route = ReleaseCalendarNavigation.destination) {
            ReleaseCalendar()
        }
        nestedGraphs()
    }
}
