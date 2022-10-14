package io.gamehub.feature.releasecalendar

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.gamehub.data.games.models.GameShort
import javax.inject.Inject

class GamesPagingSource @Inject constructor(
    private val upcomingGamesUseCase: GetUpcomingGamesUseCase,
) : PagingSource<Int, GameShort>() {
    override fun getRefreshKey(state: PagingState<Int, GameShort>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameShort> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return upcomingGamesUseCase.invoke(
            page = page,
            pageSize = pageSize
        ).fold(
            ifEmpty = {
                LoadResult.Error(RuntimeException("Failed to load"))
            },
            ifSome = { items ->
                LoadResult.Page(items,
                    prevKey = if (page > 1) page - 1 else null,
                    nextKey = if (items.isEmpty()) null else page + 1)
            }
        )
    }
}
