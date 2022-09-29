package io.gamehub.feature.home.widgets

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
import io.gamehub.core.ui.widgets.SectionWithItems
import io.gamehub.data.games.models.Game
import io.gamehub.feature.home.R
import java.time.LocalDate

@Composable
internal fun SimpleGamesSection(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    items: List<Game>,
    onExpand: () -> Unit,
    onItemClicked: (Game) -> Unit,
) {
    SectionWithItems(
        modifier = modifier,
        title = stringResource(id = titleId),
        onExpand = onExpand,
        items = items
    ) { model ->
        Column(modifier = Modifier.width(160.dp)) {
            Surface(
                modifier = Modifier.height(200.dp),
                shape = ShapeDefaults.Large,
                shadowElevation = 10.dp
            ) {
                AsyncImage(
                    modifier = Modifier.clickable { onItemClicked(model) },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(model.imageUrl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    placeholder = ColorPainter(color = Color.LightGray),
                    contentScale = ContentScale.Crop,
                )
            }
            Spacer(Modifier.height(10.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.bodyMedium,
                text = model.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview
@Composable
private fun SectionPreview() {
    GameHubTheme {
        SimpleGamesSection(
            titleId = R.string.popular_games,
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
