package io.gamehub.feature.releasecalendar.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.gamehub.core.navigation.NavigationDestination
import io.gamehub.feature.releasecalendar.ReleaseCalendar

object ReleaseCalendarNavigation : NavigationDestination {
    override val route: String = "release_calendar_route"
    override val destination: String = "release_calendar_destination"
}

fun NavGraphBuilder.releaseCalendarGraph(
    navigateToDetails: (String) -> Unit
) {
    composable(route = ReleaseCalendarNavigation.route) {
        ReleaseCalendar(
            navigateToDetails = navigateToDetails
        )
    }
}
