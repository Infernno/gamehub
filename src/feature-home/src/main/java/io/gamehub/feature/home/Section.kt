package io.gamehub.feature.home

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import io.gamehub.core.ui.theme.Dimens
import io.gamehub.data.games.models.Game

internal val ARROW_ICON_SIZE = 40.dp
internal val ARROW_PADDING_OFFSET = 5.dp

@Composable
internal fun Section(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    items: List<Game>,
    onExpand: () -> Unit,
    onItemClicked: (Game) -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeading(
            modifier = Modifier.padding(
                start = Dimens.horizontalScreenPadding,
                end = Dimens.horizontalScreenPadding - ARROW_ICON_SIZE / 2 + ARROW_PADDING_OFFSET
            ),
            text = stringResource(id = titleId),
            onExpand = onExpand
        )
        Spacer(Modifier.size(8.dp))
        SectionList(items = items, onItemClicked = onItemClicked)
    }
}

@Composable
private fun SectionHeading(
    modifier: Modifier = Modifier,
    text: String,
    onExpand: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.ExtraBold),
            modifier = Modifier.semantics { heading() }
        )
        IconButton(
            modifier = Modifier.size(ARROW_ICON_SIZE),
            onClick = onExpand
        ) {
            Icon(imageVector = Icons.Default.ArrowForwardIos, contentDescription = null)
        }
    }
}

@Composable
private fun SectionList(
    items: List<Game>,
    onItemClicked: (Game) -> Unit
) {
    LazyRow(
        contentPadding = Dimens.horizontalScreenPaddings,
        horizontalArrangement = Arrangement.spacedBy(17.dp),
    ) {
        items(items.size) { index ->
            SectionListItem(
                modifier = Modifier.width(160.dp),
                model = items[index],
                onClick = { onItemClicked(items[index]) }
            )
        }
    }
}

@Composable
private fun SectionListItem(
    model: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Column(modifier = modifier) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
            shape = ShapeDefaults.Large,
            shadowElevation = 10.dp
        ) {
            AsyncImage(
                modifier = Modifier.clickable { onClick() },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(model.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                placeholder = ColorPainter(color = Color.LightGray),
                contentScale = ContentScale.Crop,
            )
        }
        Spacer(Modifier.size(10.dp))
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
