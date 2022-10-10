package io.gamehub.core.ui.components

import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerScope
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import io.gamehub.core.ui.theme.Dimens
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
@OptIn(ExperimentalPagerApi::class)
fun <T> HubSlider(
    modifier: Modifier = Modifier,
    items: List<T>,
    itemSpacing: Dp = 10.dp,
    contentPadding: PaddingValues = Dimens.horizontalScreenPaddings,
    autoScroll: Boolean = true,
    pagerState: PagerState = rememberPagerState(),
    content: @Composable PagerScope.(page: Int) -> Unit,
) {
    HorizontalPager(
        state = pagerState,
        modifier = modifier,
        count = items.size,
        contentPadding = contentPadding,
        itemSpacing = itemSpacing,
        content = content
    )

    if (autoScroll) {
        val isDragged by pagerState.interactionSource.collectIsDraggedAsState()

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
}
