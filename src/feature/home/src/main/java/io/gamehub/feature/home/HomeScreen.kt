package io.gamehub.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.data.games.models.Game
import io.gamehub.feature.home.widgets.SimpleGamesSection
import io.gamehub.feature.home.widgets.Slider
import io.gamehub.feature.home.widgets.UpcomingGamesSection
import java.time.LocalDate

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is Default -> DefaultState(currentState)
        Loading -> LoadingState()
        Error -> ErrorState()
    }
}

@Composable
private fun DefaultState(
    state: Default,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            text = stringResource(io.gamehub.core.ui.R.string.app_name)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Slider(items = state.popularGames)
        Spacer(modifier = Modifier.height(20.dp))
        UpcomingGamesSection(
            titleId = R.string.top_upcoming_games,
            items = state.upcomingGames,
            onExpand = { },
            onItemClicked = { })
        SimpleGamesSection(
            titleId = R.string.popular_games,
            items = state.popularGames,
            onExpand = { },
            onItemClicked = { }
        )
        SimpleGamesSection(
            titleId = R.string.popular_games,
            items = state.popularGames,
            onExpand = { },
            onItemClicked = { }
        )
    }
}

@Composable
private fun LoadingState() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
private fun ErrorState() {
    Icon(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        imageVector = Icons.Default.Error,
        contentDescription = null
    )
}

@Preview
@Composable
private fun HomeScreenPreview() {
    val items = listOf(
        Game("GTA V", "", LocalDate.now()),
        Game("Witcher 3", "", LocalDate.now()),
        Game("Batman: Arkham City", "", LocalDate.now())
    )

    GameHubTheme {
        DefaultState(
            state = Default(
                popularGames = items,
                upcomingGames = items
            )
        )
    }
}
