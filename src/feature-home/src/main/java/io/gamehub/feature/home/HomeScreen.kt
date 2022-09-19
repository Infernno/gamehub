package io.gamehub.feature.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()

    when (val currentState = state.value) {
        is HomeViewModel.State.Default -> DefaultState(currentState)
        HomeViewModel.State.Loading -> LoadingState()
    }
}

@Composable
private fun DefaultState(
    state: HomeViewModel.State.Default
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()

            )
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
        Section(
            titleId = R.string.popular_games,
            items = state.popularGames,
            onExpand = { },
            onItemClicked = { }
        )
        Section(
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
