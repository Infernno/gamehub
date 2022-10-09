package io.gamehub.feature.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import io.gamehub.core.ui.theme.Dimens
import io.gamehub.data.games.models.GameShort
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
@OptIn(ExperimentalPagerApi::class)
internal fun Slider(
    modifier: Modifier = Modifier,
    items: List<GameShort>,
    onItemClicked: (GameShort) -> Unit = { },
    height: Dp = 200.dp
) {
    val pagerState = rememberPagerState()
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

    HorizontalPager(
        state = pagerState,
        modifier = modifier
            .fillMaxWidth()
            .height(height),
        count = items.size,
        contentPadding = Dimens.horizontalScreenPaddings,
        itemSpacing = 10.dp
    ) { page ->
        SliderItem(
            onClick = { onItemClicked(items[page]) },
            model = items[page],
        )
    }

    if (!isDragged) {
        LaunchedEffect(Unit) {
            while (isActive) {
                delay(4000)
                // This gets cancelled automatically on touch
                pagerState.animateScrollToPage(
                    page = (pagerState.currentPage + 1) % (pagerState.pageCount),
                )
            }
        }
    }
}

@Composable
private fun SliderItem(
    model: GameShort,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        shape = ShapeDefaults.ExtraLarge,
        shadowElevation = 10.dp,
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
}
