package io.gamehub.feature.home.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubSectionWithItems
import io.gamehub.core.ui.theme.GameHubTheme
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
        items = items,
        key = { items[it].id }
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

    Column(
        modifier = Modifier
            .width(220.dp)
            .clip(CardDefaults.shape)
            .clickable { onItemClicked() }
    ) {
        HubAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(135.dp)
                .clip(ShapeDefaults.Medium),
            data = model.imageUrl,
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
                id = 0,
                name = "Witcher 3",
                slug = "",
                genres = listOf("Action", "Adventure"),
                imageUrl = "",
                releaseDate = LocalDate.now(),
                screenshots = emptyList(),
                platforms = emptyList(),
                stores = emptyList(),
                metacritic = null
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
                id = 0,
                name = "Witcher 3",
                slug = "",
                genres = listOf("Action", "Adventure"),
                imageUrl = "",
                releaseDate = LocalDate.now(),
                screenshots = emptyList(),
                platforms = emptyList(),
                stores = emptyList(),
                metacritic = null
            )
        )
    }
}

