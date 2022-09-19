package io.gamehub.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.gamehub.R
import io.gamehub.core.navigation.NavRoutes
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.feature.home.HomeScreen
import io.gamehub.feature.search.SearchScreen

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
fun GameHubApp() = GameHubTheme {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                items = listOf(
                    BottomNavItem(
                        titleId = R.string.menu_home,
                        icon = Icons.Default.Home,
                        route = NavRoutes.Home.route
                    ),
                    BottomNavItem(
                        titleId = R.string.menu_search,
                        icon = Icons.Default.Search,
                        route = NavRoutes.Search.route
                    )
                ),
                onItemClick = { navController.navigate(it.route) }
            )
        }
    ) { padding ->
        NavigationHost(
            navController = navController,
            modifier = Modifier
                .padding(padding)
                .consumedWindowInsets(padding)
        )
    }
}

@Composable
fun NavigationHost(
    modifier: Modifier,
    navController: NavHostController
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            HomeScreen()
        }

        composable(NavRoutes.Search.route) {
            SearchScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    items: List<BottomNavItem>,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

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
            items.forEach { item ->
                NavigationBarItem(
                    selected = currentRoute == item.route,
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = stringResource(id = item.titleId))
                    },
                    onClick = {
                        onItemClick(item)
                    }
                )
            }
        }
    }
}

