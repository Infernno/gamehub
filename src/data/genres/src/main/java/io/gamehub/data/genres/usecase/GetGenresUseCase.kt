package io.gamehub.data.genres.usecase

import arrow.core.Option
import io.gamehub.data.common.Ordering
import io.gamehub.data.genres.models.Genre
import io.gamehub.data.genres.repositories.GenreRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    suspend fun getGenres(
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<Genre>> {
        return repository.getGenres(
            ordering = Ordering.ADDED,
            page = page,
            pageSize = pageSize
        ).filter { it.isNotEmpty() }
    }
}
