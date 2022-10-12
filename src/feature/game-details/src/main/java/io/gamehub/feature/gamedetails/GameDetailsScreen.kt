package io.gamehub.feature.gamedetails

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import io.gamehub.core.ui.components.ChipGroup
import io.gamehub.core.ui.components.HubAppBar
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubErrorScreen
import io.gamehub.core.ui.components.HubExpandableHtmlText
import io.gamehub.core.ui.components.HubLoadingScreen
import io.gamehub.core.ui.components.HubSection
import io.gamehub.core.ui.components.HubSlider
import io.gamehub.core.ui.components.toHmtl
import io.gamehub.core.ui.theme.horizontalScreenPaddings
import io.gamehub.data.games.models.GameDetails
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun GameDetailsScreen(
    goBack: () -> Unit,
    viewModel: GameDetailsViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        HubAppBar(
            title = stringResource(R.string.game_details),
            showBackButton = true,
            onBackButtonClicked = goBack
        )
        GameDetailsContent(
            state = state
        )
    }
}

@Composable
private fun GameDetailsContent(
    state: GameDetailsState
) {
    when (state) {
        is Default -> GameDetailsMain(state)
        Loading -> HubLoadingScreen()
        Error -> HubErrorScreen()
    }
}

@Composable
@OptIn(ExperimentalPagerApi::class)
private fun GameDetailsMain(
    state: Default
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        GameHeader(
            modifier = Modifier.horizontalScreenPaddings(),
            model = state.game
        )
        Spacer(modifier = Modifier.height(20.dp))
        GameFeatures(
            modifier = Modifier.horizontalScreenPaddings(),
            model = state.game
        )
        Spacer(modifier = Modifier.height(20.dp))
        HubSlider(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            items = state.screenshots,
        ) { index ->
            HubAsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(MaterialTheme.shapes.large),
                data = state.screenshots[index].imageUrl
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        HubSection(
            title = "Description"
        ) {
            HubExpandableHtmlText(
                modifier = Modifier.horizontalScreenPaddings(),
                style = MaterialTheme.typography.bodyMedium,
                text = remember { state.game.description.toHmtl() },
                seeMoreText = "More"
            )
            Spacer(modifier = Modifier.height(10.dp))
            ChipGroup(
                modifier = Modifier.horizontalScreenPaddings(),
                list = state.game.genres
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun GameHeader(
    modifier: Modifier = Modifier,
    model: GameDetails
) {
    val devs = remember {
        model.developers.joinToString(separator = ", ")
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HubAsyncImage(
            modifier = Modifier
                .size(70.dp)
                .clip(MaterialTheme.shapes.large),
            data = model.backgroundImageUrl
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                ),
                text = model.name,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                color = MaterialTheme.colorScheme.primary,
                text = devs,
                maxLines = 1
            )
        }
    }
}

@Composable
private fun GameFeatures(
    modifier: Modifier = Modifier,
    model: GameDetails
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min) // Otherwise fillMaxHeight doesn't work for child elements
            .border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = MaterialTheme.shapes.large
            )
            .padding(horizontal = 3.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        model.metacritic?.let {
            FeatureColumn(
                painter = painterResource(R.drawable.ic_metacritic_logo),
                iconTint = Color.Unspecified,
                title = it.toString(),
                body = stringResource(R.string.metacritic)
            )
        }
        model.playtime?.let {
            // For some reason, playtime can be null or zero
            if (it > 0) {
                Divider(
                    modifier = Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                )
                FeatureColumn(
                    painter = rememberVectorPainter(Icons.Default.AccessTime),
                    title = stringResource(R.string.playtime_hours, it),
                    body = stringResource(R.string.playtime)
                )
            }
        }

    }
}

@Composable
private fun FeatureColumn(
    painter: Painter,
    title: String,
    body: String,
    iconTint: Color = LocalContentColor.current
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painter,
                contentDescription = title,
                tint = iconTint
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall
            )
        }
        Spacer(modifier = Modifier.height(7.dp))
        Text(
            text = body,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
