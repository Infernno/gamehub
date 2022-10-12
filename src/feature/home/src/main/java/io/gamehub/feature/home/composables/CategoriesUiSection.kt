package io.gamehub.feature.home.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubSectionWithItems
import io.gamehub.data.games.models.Genre

private val CELL_SIZE = 120.dp

@Composable
internal fun CategoriesUiSection(
    modifier: Modifier = Modifier,
    items: List<Genre>,
    @StringRes titleId: Int,
    @StringRes subtitleId: Int?,
    onItemClicked: (Genre) -> Unit,
    onExpand: (() -> Unit)? = null,
) {
    HubSectionWithItems(
        modifier = modifier,
        title = stringResource(titleId),
        subtitle = subtitleId?.let { stringResource(it) },
        onExpand = onExpand,
        items = items
    ) { item ->
        Column(
            modifier = Modifier.width(CELL_SIZE),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HubAsyncImage(
                modifier = Modifier
                    .size(CELL_SIZE)
                    .clip(ShapeDefaults.ExtraLarge)
                    .shadow(10.dp)
                    .clickable { onItemClicked(item) },
                data = item.imageUrl,
                placeholder = ColorPainter(color = Color.LightGray),
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = item.name,
                maxLines = 1,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
