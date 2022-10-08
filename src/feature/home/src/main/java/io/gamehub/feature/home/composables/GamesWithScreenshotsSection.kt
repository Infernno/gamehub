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
import io.gamehub.core.ui.widgets.HubSectionWithItems
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.models.GameWithScreenshots
import io.gamehub.data.games.models.Screenshot
import java.time.LocalDate

@Composable
internal fun GamesWithScreenshotsSection(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    items: List<GameWithScreenshots>,
    onExpand: (() -> Unit)? = null,
    onItemClicked: (GameWithScreenshots) -> Unit,
) {
    HubSectionWithItems(
        modifier = modifier,
        title = stringResource(titleId),
        onExpand = onExpand,
        items = items
    ) { item ->
        UpcomingGame(
            modifier = Modifier
                .width(230.dp)
                .height(190.dp),
            model = item,
            onClick = { onItemClicked(item) }
        )
    }
}

@Composable
private fun UpcomingGame(
    modifier: Modifier = Modifier,
    model: GameWithScreenshots,
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
                    .data(model.game.imageUrl)
                    .placeholder(android.R.color.darker_gray)
                    .crossfade(true)
                    .build(),
                contentDescription = model.game.name,
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
                    Text(model.game.name, maxLines = 1)
                    Text(model.game.releaseDate.toString(), maxLines = 1)
                }
            }
        }
    }
}

@Preview
@Composable
private fun SectionItemPreview() {
    GameHubTheme(darkTheme = true) {
        UpcomingGame(
            model = GameWithScreenshots(
                game =  Game(
                    name = "Gotham Knights",
                    slug = "gotham-knights",
                    imageUrl = "https://media.rawg.io/media/resize/1920/-/screenshots/dc0/dc002c5dc73e119d7516f8001b5d3ac9.jpg",
                    releaseDate = LocalDate.of(2022, 10, 6)
                ),
                screenshot = listOf(
                    Screenshot(
                        imageUrl = "https://media.rawg.io/media/resize/1920/-/screenshots/5df/5df102d9a25d5cb8b307e2f5fc678bdb_ysuuyhG.jpg"
                    ),
                    Screenshot(
                        imageUrl = "https://media.rawg.io/media/resize/1920/-/screenshots/693/693584d2a8bee41ed66b90c8f682d77e.jpg"
                    )
                )
            )
        )
    }
}
