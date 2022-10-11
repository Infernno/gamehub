package io.gamehub.feature.releasecalendar

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.gamehub.core.utils.extensions.executeSuspendSafe
import io.gamehub.data.common.DateRange
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.usecase.GetUpcomingGamesUseCase
import java.time.LocalDate
import javax.inject.Inject

class GamesPagingSource @Inject constructor(
    private val upcomingGamesUseCase: GetUpcomingGamesUseCase
) : PagingSource<Int, GameShort>() {
    override fun getRefreshKey(state: PagingState<Int, GameShort>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameShort> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        val result = executeSuspendSafe {
            upcomingGamesUseCase.invoke(
                dates = DateRange.single(LocalDate.now()),
                page = page,
                pageSize = pageSize
            )
        }.onFailure {
            println(it)
        }

        if(result.isSuccess) {
            val items = result.getOrThrow()

            return LoadResult.Page(
                items,
                prevKey = if (page > 1) page - 1 else null,
                nextKey = if (items.isEmpty()) null else page + 1
            )
        }

        return LoadResult.Error(
            result.exceptionOrNull()!!
        )
    }
}
