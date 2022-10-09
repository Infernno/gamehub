package io.gamehub.core.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gamehub.core.ui.theme.Dimens
import io.gamehub.core.ui.theme.GameHubTheme

internal val ARROW_ICON_SIZE = 40.dp
internal val ARROW_PADDING_OFFSET = 5.dp

@Composable
fun HubSection(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    onExpand: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SectionHeading(
            modifier = Modifier.padding(
                start = Dimens.horizontalScreenPadding,
                end = Dimens.horizontalScreenPadding - ARROW_ICON_SIZE / 2 + ARROW_PADDING_OFFSET
            ),
            text = title,
            subtitle = subtitle,
            onExpand = onExpand
        )
        Spacer(Modifier.height(20.dp))
        content()
    }
}

@Composable
fun <T> HubSectionWithItems(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String? = null,
    onExpand: (() -> Unit)? = null,
    items: List<T>,
    itemContent: @Composable LazyItemScope.(item: T) -> Unit,
) {
    HubSection(
        modifier = modifier,
        title = title,
        subtitle = subtitle,
        onExpand = onExpand
    ) {
        LazyRow(
            contentPadding = Dimens.horizontalScreenPaddings,
            horizontalArrangement = Arrangement.spacedBy(17.dp),
        ) {
            items(items.size) { index ->
                itemContent(this, items[index])
            }
        }
    }
}

@Composable
private fun SectionHeading(
    modifier: Modifier = Modifier,
    text: String,
    subtitle: String? = null,
    onExpand: (() -> Unit)? = null,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth()
    ) {
        Column {
            Text(
                text = text,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
            )
            if(!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.LightGray
                )
            }
        }
        if (onExpand != null) {
            IconButton(
                modifier = Modifier.size(ARROW_ICON_SIZE),
                onClick = onExpand
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview
@Composable
private fun SectionPreview() {
    GameHubTheme {
        HubSection(
            title = "This is a title",
            subtitle = "This is a subtitle",
            onExpand = { },
            content = {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Hello world!"
                )
            }
        )
    }
}

@Preview
@Composable
private fun SectionWithItemsPreview() {
    GameHubTheme(darkTheme = true) {
        HubSectionWithItems(
            title = "This is a title",
            subtitle = "This is a subtitle",
            onExpand = { },
            items = listOf(
                Color.Blue,
                Color.Green,
            )
        ) { item ->
            Surface(
                modifier = Modifier.size(200.dp),
                shape = ShapeDefaults.Large,
                shadowElevation = 10.dp,
                color = item
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center),
                    textAlign = TextAlign.Center,
                    text = "Hello world!",
                )
            }
        }
    }
}
