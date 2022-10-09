package io.gamehub.feature.gamedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.gamehub.data.games.models.GameDetails
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun GameDetailsScreen(
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    GameDetailsContent(
        state = state
    )
}

@Composable
private fun GameDetailsContent(
    state: GameDetailsState
) {
    when (state) {
        is Default -> GameDetailsMain(state.model)
        Error -> ErrorState()
        Loading -> LoadingState()
    }
}

@Composable
private fun GameDetailsMain(
    model: GameDetails
) {
    Column {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f),
            model = ImageRequest.Builder(LocalContext.current)
                .data(model.backgroundImageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = ColorPainter(color = Color.LightGray),
            contentScale = ContentScale.Crop,
        )
        Text(
            text = model.name
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

