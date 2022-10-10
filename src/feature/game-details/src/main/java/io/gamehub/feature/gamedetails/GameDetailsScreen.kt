package io.gamehub.feature.gamedetails

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubExpandableHtmlText
import io.gamehub.core.ui.components.toHmtl
import io.gamehub.core.ui.theme.horizontalScreenPaddings
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
    val background = MaterialTheme.colorScheme.onBackground

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        HubAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(340.dp),
            /*
        .drawWithCache {
            val gradient = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    background
                ),
                startY = size.height / 4,
                endY = size.height
            )
            onDrawWithContent {
                drawContent()
                drawRect(gradient, blendMode = BlendMode.DstIn)
            }
        }*/
            data = model.backgroundImageUrl,
        )
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            modifier = Modifier.horizontalScreenPaddings()
        ) {
            Text(
                style = MaterialTheme.typography.titleLarge,
                text = model.name
            )
            Spacer(modifier = Modifier.height(10.dp))
            HubExpandableHtmlText(
                style = MaterialTheme.typography.bodyMedium,
                text = remember { model.description.toHmtl() },
                seeMoreText = "More"
            )
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

