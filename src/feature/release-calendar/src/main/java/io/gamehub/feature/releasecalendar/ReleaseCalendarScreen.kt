package io.gamehub.feature.releasecalendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import io.gamehub.core.ui.components.HubAppBar
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubErrorScreen
import io.gamehub.core.ui.components.HubLoadingScreen
import io.gamehub.core.ui.extensions.itemsIndexed
import io.gamehub.core.ui.theme.Dimens
import io.gamehub.data.games.models.GameShort
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ReleaseCalendar(
    navigateToDetails: (String) -> Unit,
) {
    ReleaseCalendar(
        viewModel = hiltViewModel(),
        navigateToDetails = navigateToDetails
    )
}

@Composable
internal fun ReleaseCalendar(
    viewModel: ReleaseCalendarViewModel,
    navigateToDetails: (String) -> Unit,
) {
    val pagingItems = viewModel.games.collectAsLazyPagingItems()

    when (pagingItems.loadState.refresh) {
        is LoadState.Error -> HubErrorScreen()
        is LoadState.NotLoading -> DefaultState(pagingItems, navigateToDetails)
        LoadState.Loading -> HubLoadingScreen()
    }
}

@Composable
private fun DefaultState(
    pagingItems: LazyPagingItems<GameShort>,
    navigateToDetails: (String) -> Unit,
) {
    val formatter = remember {
        DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale.US) // TODO: Add localization
    }

    Column {
        HubAppBar(
            title = stringResource(R.string.release_calendar_title)
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(
                vertical = 10.dp,
                horizontal = Dimens.horizontalScreenPadding
            ),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalArrangement = Arrangement.spacedBy(Dimens.horizontalScreenPadding)
        ) {
            itemsIndexed(pagingItems, key = { _, i -> i.id }) { _, item ->
                ListItem(
                    item = requireNotNull(item),
                    formatter = formatter,
                    navigateToDetails = navigateToDetails
                )
            }
        }
    }
}

@Composable
private fun ListItem(
    item: GameShort,
    formatter: DateTimeFormatter,
    navigateToDetails: (String) -> Unit,
) {
    val genres = remember {
        item.genres.joinToString(separator = ", ")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetails(item.slug) }
    ) {
        HubAsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .shadow(5.dp, MaterialTheme.shapes.large, true),
            data = item.imageUrl
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = item.name,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            maxLines = 1
        )
        Spacer(modifier = Modifier.height(1.dp))
        if (genres.isNotBlank()) {
            Text(
                modifier = Modifier.padding(horizontal = 4.dp),
                text = genres,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
            )
            Spacer(modifier = Modifier.height(9.dp))
        }
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = formatter.format(item.releaseDate),
            style = MaterialTheme.typography.titleSmall,
        )
    }
}
