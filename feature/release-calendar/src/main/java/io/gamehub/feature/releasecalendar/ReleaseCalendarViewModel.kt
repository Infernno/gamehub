package io.gamehub.feature.releasecalendar

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.data.games.models.GameShort
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ReleaseCalendarViewModel @Inject constructor(
    private val gamesPagingSource: Lazy<GamesPagingSource>
) : ViewModel() {
    val games: Flow<PagingData<GameShort>> =
        Pager(PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE)) { gamesPagingSource.get() }
            .flow
            .cachedIn(viewModelScope)
            .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private companion object {
        const val PAGE_SIZE = 30
    }
}
