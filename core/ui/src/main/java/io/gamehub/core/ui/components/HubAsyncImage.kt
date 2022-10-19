package io.gamehub.core.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HubAsyncImage(
    modifier: Modifier = Modifier,
    data: String?,
    placeholder: ColorPainter = ColorPainter(color = Color.LightGray)
) = AsyncImage(
    modifier = modifier,
    model = ImageRequest.Builder(LocalContext.current)
        .data(data)
        .crossfade(true)
        .build(),
    contentDescription = null,
    placeholder = placeholder,
    contentScale = ContentScale.Crop,
)
