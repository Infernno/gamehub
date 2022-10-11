package io.gamehub.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun ChipGroup(
    modifier: Modifier = Modifier,
    list: List<String>,
) {
    LazyRow(
        modifier = modifier
    ) {
        items(list.size) { index ->
            Chip(
                content = list[index]
            )
        }
    }
}

@Composable
fun Chip(
    content: String,
    shape: Shape = MaterialTheme.shapes.large,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    textColor: Color = MaterialTheme.colorScheme.secondary
) {
    Surface(
        modifier = Modifier.padding(
            horizontal = 4.dp,
            vertical = 3.dp
        ),
        shape = shape,
        color = backgroundColor
    ) {
        Text(
            text = content,
            style = MaterialTheme.typography.bodySmall,
            color = textColor,
            modifier = Modifier.padding(8.dp)
        )
    }
}
