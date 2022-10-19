package io.gamehub.feature.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.gamehub.core.ui.components.HubAsyncImage
import io.gamehub.core.ui.components.HubErrorScreen
import io.gamehub.core.ui.components.HubLoadingScreen
import io.gamehub.core.ui.theme.Dimens
import io.gamehub.data.games.models.GameShort
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import org.orbitmvi.orbit.compose.collectAsState
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun SearchScreen(
    goBack: () -> Unit,
    navigateToDetails: (String) -> Unit,
) {
    SearchScreen(
        viewModel = hiltViewModel(),
        navigateToDetails = navigateToDetails,
        goBack = goBack
    )
}

@Composable
internal fun SearchScreen(
    viewModel: SearchViewModel,
    goBack: () -> Unit,
    navigateToDetails: (String) -> Unit,
) {
    val state by viewModel.collectAsState()

    Column {
        SearchBar(
            onSearch = viewModel::search,
            goBack = goBack
        )

        when (val currentState = state) {
            Default -> {}
            Error -> HubErrorScreen()
            Loading -> HubLoadingScreen()
            NotFound -> NothingFound()
            is Found -> SearchList(currentState.items, navigateToDetails)
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, FlowPreview::class)
private fun SearchBar(
    onSearch: (String) -> Unit,
    goBack: () -> Unit,
) {
    val searchFlow = remember {
        MutableStateFlow("")
    }

    LaunchedEffect(searchFlow) {
        // Don't need to remember here because the launched effect lives across recompositions.
        searchFlow
            .filter { it.length > 3 && it.isNotBlank() }
            .debounce(1500)
            .collect { onSearch(it) }
    }

    var queryState: String by rememberSaveable { mutableStateOf("") }
    var showClearIcon by rememberSaveable { mutableStateOf(false) }

    showClearIcon = queryState.isNotEmpty()

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = queryState,
        onValueChange = { onQueryChanged ->
            queryState = onQueryChanged

            if (onQueryChanged.isNotEmpty()) {
                searchFlow.value = queryState
            }
        },
        leadingIcon = {
            IconButton(onClick = goBack) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = null
                )
            }
        },
        trailingIcon = {
            if (showClearIcon) {
                IconButton(onClick = { queryState = "" }) {
                    Icon(
                        imageVector = Icons.Rounded.Clear,
                        contentDescription = null
                    )
                }
            }
        },
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.Transparent
        ),
        maxLines = 1,
        placeholder = { Text(text = stringResource(R.string.hint_search_query)) },
        textStyle = MaterialTheme.typography.titleMedium,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )
}

@Composable
private fun SearchList(
    games: List<GameShort>,
    navigateToDetails: (String) -> Unit,
) {
    val formatter = remember {
        DateTimeFormatter.ofPattern("dd LLLL yyyy", Locale.US) // TODO: Add localization
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(
            vertical = 10.dp,
            horizontal = Dimens.horizontalScreenPadding
        ),
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalArrangement = Arrangement.spacedBy(Dimens.horizontalScreenPadding)
    ) {
        items(games.size) { index ->
            SearchListItem(
                item = games[index],
                formatter = formatter,
                navigateToDetails = navigateToDetails
            )
        }
    }
}

@Composable
private fun SearchListItem(
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

@Composable
private fun NothingFound(
) {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center),
        text = stringResource(R.string.nothing_found),
        style = MaterialTheme.typography.titleLarge
    )
}
