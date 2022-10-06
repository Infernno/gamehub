package io.gamehub.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.feature.home.HomeScreen

@Composable
@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
)
fun GameHubApp() = GameHubTheme {
    Scaffold { padding ->
        Box(modifier = Modifier
            .padding(padding)
            .consumedWindowInsets(padding)) {
            HomeScreen()
        }
    }
}
