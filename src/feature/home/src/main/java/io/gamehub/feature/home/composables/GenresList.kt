package io.gamehub.feature.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.gamehub.core.ui.theme.GameHubTheme
import io.gamehub.core.ui.widgets.SectionWithItems
import io.gamehub.data.genres.models.Genre

@Composable
internal fun GenresList(
    items: List<Genre>,
    onItemClicked: (Genre) -> Unit,
    onExpand: () -> Unit
) {
    SectionWithItems(
        title = "Genres",
        onExpand = onExpand,
        items = items
    ) { item ->
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(MaterialTheme.shapes.large)
                .clickable { onItemClicked(item) },
            contentAlignment = Alignment.Center
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(item.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = item.name,
                placeholder = ColorPainter(color = Color.LightGray),
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.Black.copy(alpha = 0.5f))
            )
            Text(
                color = Color.White,
                text = item.name,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun GenresListPreview() {
    GameHubTheme {
        val genres = listOf(
            Genre(0, "MMORPG", "mmorpg", 1, ""),
            Genre(1, "Strategy", "strategy", 2, ""),
            Genre(2, "Shooter", "shooter", 3, "")
        )

        GenresList(items = genres, onItemClicked = { }, onExpand = { })
    }
}
