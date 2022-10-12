package io.gamehub.feature.filter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.gamehub.core.common.utils.executeSuspendSafe
import io.gamehub.data.common.DateRange
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.usecase.GetGamesByGenreUseCase
import io.gamehub.data.games.usecase.GetUpcomingGamesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDate

class FilterScreenPagingSource @AssistedInject constructor(
    @Assisted private val filterValue: String,
    private val usecase: GetGamesByGenreUseCase,
) : PagingSource<Int, GameShort>() {

    override fun getRefreshKey(state: PagingState<Int, GameShort>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameShort> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        val result = executeSuspendSafe {
            withContext(Dispatchers.IO) {
                usecase.invoke(
                    genre = filterValue,
                    page = page,
                    pageSize = pageSize
                ).sortedBy { it.releaseDate }
            }
        }.onFailure {
            println(it)
        }

        if (result.isSuccess) {
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

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted filterKey: String
        ): FilterScreenPagingSource
    }
}
