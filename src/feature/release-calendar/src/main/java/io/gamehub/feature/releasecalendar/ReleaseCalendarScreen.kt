package io.gamehub.feature.releasecalendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import io.gamehub.core.ui.components.ChipGroup
import io.gamehub.core.ui.components.HubAppBar
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.data.games.models.GameShort
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ReleaseCalendar(
    viewModel: ReleaseCalendarViewModel = hiltViewModel(),
    navigateToDetails: (String) -> Unit
) {
    val pagingItems = viewModel.games.collectAsLazyPagingItems()

    when (pagingItems.loadState.refresh) {
        is LoadState.Error -> ErrorState()
        is LoadState.NotLoading -> DefaultState(pagingItems, navigateToDetails)
        LoadState.Loading -> LoadingState()
    }
}

@Composable
private fun DefaultState(
    pagingItems: LazyPagingItems<GameShort>,
    navigateToDetails: (String) -> Unit
) {
    val formatter = remember {
        DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale.US) // TODO: Add localization
    }

    Column {
        HubAppBar(
            title = stringResource(R.string.release_calendar_title)
        )
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(vertical = 20.dp)
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
    navigateToDetails: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navigateToDetails(item.slug) }
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HubAsyncImage(
            modifier = Modifier
                .width(150.dp)
                .height(120.dp)
                .shadow(5.dp, MaterialTheme.shapes.large, true),
            data = item.imageUrl
        )
        Spacer(modifier = Modifier.width(15.dp))
        Column(
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = formatter.format(item.releaseDate),
                style = MaterialTheme.typography.bodyMedium,
            )
            Spacer(modifier = Modifier.height(6.dp))
            ChipGroup(
                list = item.platforms
            )
        }
    }
}

@Composable
private fun LoadingState() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
private fun ErrorState() {
    Icon(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        imageVector = Icons.Default.Error,
        contentDescription = null
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReleaseCalendar(
        navigateToDetails = { }
    )
}
