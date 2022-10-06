package io.gamehub.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import io.gamehub.R

/*
enum class BottomBarItem(
    val direction: DirectionDestinationSpec,
    @StringRes val label: Int,
    val icon: ImageVector,
) {
    Home(HomeScreenDestination, R.string.menu_home, Icons.Default.Home),
    // UpcomingGames(SearchScreenDestination, R.string.menu_upcoming, Icons.Default.CalendarMonth),
    Search(SearchScreenDestination, R.string.menu_search, Icons.Default.Search),
}

@Composable
fun HubBottomBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    // Wrap the navigation bar in a surface so the color behind the system
    // navigation is equal to the container color of the navigation bar.
    Surface(
        color = NavigationBarDefaults.containerColor,
        tonalElevation = NavigationBarDefaults.Elevation
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            tonalElevation = 0.dp,
            modifier = modifier.windowInsetsPadding(
                WindowInsets.safeDrawing.only(
                    WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
                )
            ),
        ) {
            BottomBarItem.values().forEach { destination ->
                val isCurrentDestOnBackStack =
                    navController.isRouteOnBackStack(destination.direction)
                NavigationBarItem(
                    selected = isCurrentDestOnBackStack,
                    icon = {
                        Icon(
                            imageVector = destination.icon,
                            contentDescription = stringResource(destination.label)
                        )
                    },
                    label = {
                        Text(text = stringResource(destination.label))
                    },
                    onClick = {
                        if (isCurrentDestOnBackStack) {
                            // When we click again on a bottom bar item and it was already selected
                            // we want to pop the back stack until the initial destination of this bottom bar item
                            navController.popBackStack(destination.direction, false)
                            return@NavigationBarItem
                        }

                        navController.navigate(destination.direction) {
                            // Pop up to the root of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(rootNavGraph) {
                                saveState = true
                            }

                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
*/
