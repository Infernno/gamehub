package io.gamehub.feature.filter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import io.gamehub.data.games.models.GameShort

class FilterScreenPagingSource @AssistedInject constructor(
    @Assisted private val filterValue: String,
    private val findGamesByGenreUseCase: FindGamesByGenreUseCase,
) : PagingSource<Int, GameShort>() {

    override fun getRefreshKey(state: PagingState<Int, GameShort>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GameShort> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        return findGamesByGenreUseCase.invoke(
            genre = filterValue,
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

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted filterKey: String,
        ): FilterScreenPagingSource
    }
}
