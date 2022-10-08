package io.gamehub.feature.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.gamehub.data.games.models.Game
import io.gamehub.data.genres.models.Genre
import io.gamehub.feature.home.composables.GamesSection
import io.gamehub.feature.home.composables.GamesWithScreenshotsSection
import io.gamehub.feature.home.composables.GenresSection
import io.gamehub.feature.home.composables.Slider
import io.gamehub.feature.home.models.GamesSection
import io.gamehub.feature.home.models.GamesWithScreenshotsSection
import io.gamehub.feature.home.models.GenresSection
import io.gamehub.feature.home.models.SliderSection
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    when (val currentState = state) {
        is Default -> DefaultState(
            state = currentState,
            openGameDetails = { },
            openGamesOfGenre = { },
        )
        Loading -> LoadingState()
        Error -> ErrorState()
    }
}

@Composable
private fun DefaultState(
    state: Default,
    openGameDetails: (Game) -> Unit,
    openGamesOfGenre: (Genre) -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                text = stringResource(io.gamehub.core.ui.R.string.app_name)
            )
            Spacer(modifier = Modifier.height(20.dp))
        }

        items(state.sections.size) { index ->
            val modifier = Modifier.padding(
                top = if (index == 0) 0.dp else 4.dp,
                bottom = 20.dp
            )

            when (val section = state.sections[index]) {
                is GamesSection -> GamesSection(
                    modifier = modifier,
                    titleId = section.titleId,
                    items = section.items,
                    onItemClicked = { openGameDetails(it) }
                )
                is GamesWithScreenshotsSection -> GamesWithScreenshotsSection(
                    modifier = modifier,
                    titleId = section.titleId,
                    items = section.items,
                    onItemClicked = { openGameDetails(it.game) }
                )
                is GenresSection -> GenresSection(
                    modifier = modifier,
                    items = section.items,
                    onItemClicked = { openGamesOfGenre(it) }
                )
                is SliderSection -> Slider(
                    modifier = modifier,
                    items = section.items,
                    onItemClicked = { openGameDetails(it) }
                )
            }
        }
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
