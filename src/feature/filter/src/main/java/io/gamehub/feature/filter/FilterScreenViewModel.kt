package io.gamehub.feature.filter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.core.common.utils.navArgs
import io.gamehub.data.games.models.GameShort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class FilterScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val pagingSource: FilterScreenPagingSource.Factory,
) : ViewModel() {

    private val arg = savedStateHandle.navArgs<String>(FILTER_ARG_KEY)

    val games: Flow<PagingData<GameShort>> =
        Pager(PagingConfig(pageSize = 20)) { pagingSource.create(arg) }
            .flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}
