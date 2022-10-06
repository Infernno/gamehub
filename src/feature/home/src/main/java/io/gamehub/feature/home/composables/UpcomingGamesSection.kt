package io.gamehub.feature.home.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.core.ui.widgets.SectionWithItems
import io.gamehub.data.games.models.Game
import io.gamehub.feature.home.R
import java.time.LocalDate

@Composable
internal fun UpcomingGamesSection(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    items: List<Game>,
    onExpand: () -> Unit,
    onItemClicked: (Game) -> Unit,
) {
    SectionWithItems(
        modifier = modifier,
        title = stringResource(titleId),
        onExpand = onExpand,
        items = items
    ) { item ->
        UpcomingGameCard(
            modifier = Modifier
                .width(230.dp)
                .height(190.dp),
            model = item,
            onClick = { onItemClicked(item) }
        )
    }
}

@Composable
private fun UpcomingGameCard(
    model: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { },
) {
    Surface(
        shape = ShapeDefaults.Large,
        shadowElevation = 10.dp
    ) {
        Box(
            modifier = modifier.clickable { onClick() },
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = model.name,
                placeholder = ColorPainter(color = Color.LightGray),
                contentScale = ContentScale.Crop,
            )
            Surface(
                color = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(model.name, maxLines = 1)
                    Text(model.releaseDate.toString(), maxLines = 1)
                }
            }
        }
    }
}

@Preview
@Composable
private fun SectionPreview() {
    GameHubTheme {
        UpcomingGamesSection(
            titleId = R.string.top_upcoming_games,
            items = listOf(
                Game("GTA V", "", LocalDate.now()),
                Game("Witcher 3", "", LocalDate.now()),
                Game("Batman: Arkham City", "", LocalDate.now())
            ),
            onExpand = { },
            onItemClicked = { }
        )
    }
}

@Preview
@Composable
private fun SectionPreviewDark() {
    GameHubTheme(darkTheme = true) {
        UpcomingGamesSection(
            titleId = R.string.top_upcoming_games,
            items = listOf(
                Game("GTA V", "", LocalDate.now()),
                Game("Witcher 3", "", LocalDate.now()),
                Game("Batman: Arkham City", "", LocalDate.now())
            ),
            onExpand = { },
            onItemClicked = { }
        )
    }
}
