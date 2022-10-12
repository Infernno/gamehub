package io.gamehub.feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.gamehub.core.ui.components.HubErrorScreen
import io.gamehub.core.ui.components.HubLoadingScreen
import io.gamehub.core.ui.theme.horizontalScreenPaddings
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.models.Genre
import io.gamehub.feature.home.composables.CategoriesUiSection
import io.gamehub.feature.home.composables.GamesSlider
import io.gamehub.feature.home.composables.GamesUiSection
import io.gamehub.feature.home.models.CategoriesSection
import io.gamehub.feature.home.models.GamesSection
import io.gamehub.feature.home.models.SliderSection
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit,
    navigateToSearch: () -> Unit,
) {
    val state by viewModel.collectAsState()

    when (val currentState = state) {
        is Default -> DefaultState(
            state = currentState,
            openGameDetails = { navigateToDetails(it.slug) },
            openGamesOfGenre = { },
            openSearch = navigateToSearch
        )
        Loading -> HubLoadingScreen()
        Error -> HubErrorScreen()
    }
}

@Composable
private fun DefaultState(
    state: Default,
    openGameDetails: (GameShort) -> Unit,
    openGamesOfGenre: (Genre) -> Unit,
    openSearch: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
    ) {
        item {
            Spacer(modifier = Modifier.height(20.dp))
            SearchViewStub(
                modifier = Modifier.horizontalScreenPaddings(),
                text = stringResource(R.string.search_bar),
                onClicked = openSearch
            )
            Spacer(modifier = Modifier.height(20.dp))
        }
        items(state.sections.size) { index ->
            val modifier = Modifier.padding(
                top = if (index == 0) 0.dp else 4.dp,
                bottom = 20.dp
            )

            when (val section = state.sections[index]) {
                is SliderSection -> GamesSlider(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    items = section.items,
                    onItemClicked = { openGameDetails(it) }
                )
                is CategoriesSection -> CategoriesUiSection(
                    modifier = modifier,
                    titleId = section.titleId,
                    subtitleId = section.subtitleId,
                    items = section.items,
                    onItemClicked = { openGamesOfGenre(it) }
                )
                is GamesSection -> GamesUiSection(
                    modifier = modifier,
                    titleId = section.titleId,
                    subtitleId = section.subtitleId,
                    items = section.items,
                    onItemClicked = { openGameDetails(it) }
                )
            }
        }
    }
}

@Composable
private fun SearchViewStub(
    modifier: Modifier = Modifier,
    text: String,
    onClicked: () -> Unit,
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = CircleShape,
        tonalElevation = 3.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .clickable { onClicked() }
                .padding(horizontal = 15.dp, vertical = 13.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}
