package io.gamehub.feature.home.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.core.ui.components.HubSectionWithItems
import io.gamehub.data.games.models.GameShort
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


@Composable
internal fun GamesUiSection(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    @StringRes subtitleId: Int?,
    items: List<GameShort>,
    onExpand: (() -> Unit)? = null,
    onItemClicked: (GameShort) -> Unit,
) {
    HubSectionWithItems(
        modifier = modifier,
        title = stringResource(id = titleId),
        subtitle = subtitleId?.let { stringResource(it) },
        onExpand = onExpand,
        items = items
    ) { item ->
        GameItem(
            model = item,
            onItemClicked = { onItemClicked(item) }
        )
    }
}

@Composable
private fun GameItem(
    model: GameShort,
    onItemClicked: () -> Unit = { },
) {
    val genres = remember {
        model.genres.joinToString(", ")
    }

    val releaseDate = remember {
        model.releaseDate?.let {
            DateTimeFormatter
                .ofPattern("d LLLL yyyy")
                .withLocale(Locale.US) // TODO: Add localization
                .format(it)
        }
    }

    Column(
        modifier = Modifier
            .width(220.dp)
          //  .clip(CardDefaults.shape)
        //    .clickable { onItemClicked() }
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(135.dp)
                .clip(ShapeDefaults.Medium),
            model = ImageRequest.Builder(LocalContext.current)
                .data(model.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = null,
            placeholder = ColorPainter(color = Color.LightGray),
            contentScale = ContentScale.Crop,
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            modifier = Modifier.padding(horizontal = 2.dp),
            style = MaterialTheme.typography.titleMedium,
            text = model.name,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Left
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier.padding(horizontal = 2.dp),
            style = MaterialTheme.typography.labelMedium,
            text = genres,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Left
        )
    }
}

@Preview
@Composable
private fun ItemPreview() {
    GameHubTheme(darkTheme = false) {
        GameItem(
            model = GameShort(
                name = "Witcher 3",
                slug = "",
                genres = listOf("Action", "Adventure"),
                imageUrl = "",
                releaseDate = LocalDate.now(),
                screenshots = emptyList()
            )
        )
    }
}

@Preview
@Composable
private fun ItemPreviewDark() {
    GameHubTheme(darkTheme = true) {
        GameItem(
            model = GameShort(
                name = "Witcher 3",
                slug = "",
                genres = listOf("Action", "Adventure"),
                imageUrl = "",
                releaseDate = LocalDate.now(),
                screenshots = emptyList()
            )
        )
    }
}
