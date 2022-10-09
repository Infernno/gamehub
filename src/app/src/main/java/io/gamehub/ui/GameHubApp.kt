package io.gamehub.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.navigation.HubBottomBar
import io.gamehub.navigation.HubNavHost

@Composable
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
)
fun GameHubApp(
    appState: GameHubAppState = rememberHubAppState()
) = GameHubTheme {
    Scaffold(
        bottomBar = {
            HubBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigate,
                currentDestination = appState.currentDestination
            )
        }
    ) { padding ->
        HubNavHost(
            navController = appState.navController,
            onBackClick = appState::onBackClick,
            onNavigateToDestination = appState::navigate,
            modifier = Modifier
                .padding(padding)
                .consumedWindowInsets(padding)
        )
    }
}
