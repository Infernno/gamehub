package io.gamehub.feature.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubSlider
import io.gamehub.core.ui.theme.Dimens
import io.gamehub.data.games.models.GameShort

@Composable
@OptIn(ExperimentalPagerApi::class)
internal fun GamesSlider(
    modifier: Modifier = Modifier,
    items: List<GameShort>,
    onItemClicked: (GameShort) -> Unit = { },
) {
    HubSlider(
        modifier = modifier,
        items = items,
        contentPadding = Dimens.horizontalScreenPaddings,
        itemSpacing = 10.dp
    ) { page ->
        SliderItem(
            onClick = { onItemClicked(items[page]) },
            model = items[page],
        )
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
        HubAsyncImage(
            modifier = Modifier.clickable { onClick() },
            data = model.imageUrl
        )
    }
}
