package io.gamehub.feature.gamedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubBackButton
import io.gamehub.core.ui.components.HubExpandableHtmlText
import io.gamehub.core.ui.components.HubSlider
import io.gamehub.core.ui.components.toHmtl
import io.gamehub.core.ui.theme.horizontalScreenPaddings
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
        is Default -> GameDetailsMain(state)
        Error -> ErrorState()
        Loading -> LoadingState()
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class, ExperimentalMaterial3Api::class)
private fun GameDetailsMain(
    state: Default
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = {
                Text(text = state.game.name)
            }
        )
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {
            HubSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                items = state.screenshots,
            ) { index ->
                HubAsyncImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(MaterialTheme.shapes.extraLarge),
                    data = state.screenshots[index].imageUrl
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Column(
                modifier = Modifier.horizontalScreenPaddings()
            ) {
                Text(
                    style = MaterialTheme.typography.titleLarge,
                    text = state.game.name
                )
                Spacer(modifier = Modifier.height(10.dp))
                HubExpandableHtmlText(
                    style = MaterialTheme.typography.bodyMedium,
                    text = remember { state.game.description.toHmtl() },
                    seeMoreText = "More"
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
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

