package io.gamehub.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.navigation.HubBottomBar
import io.gamehub.navigation.HubNavHost
import io.gamehub.navigation.RootScreen

@Composable
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
)
fun GameHubApp() = GameHubTheme {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            HubBottomBar(
                navController = navController
            )
        }
    ) { padding ->
        HubNavHost(
            navController = navController,
            modifier = Modifier
                .padding(padding)
                .consumedWindowInsets(padding)
        )
    }
}
