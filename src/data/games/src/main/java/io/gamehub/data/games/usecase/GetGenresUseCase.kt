package io.gamehub.data.games.usecase

import io.gamehub.data.games.common.Ordering
import io.gamehub.data.games.models.Genre
import io.gamehub.data.games.repository.GenreRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val repository: GenreRepository
) {
    suspend fun getGenres(
        page: Int? = null,
        pageSize: Int? = null,
    ): List<Genre> {
        return repository.getGenres(
            ordering = Ordering.ADDED_REVERSED,
            page = page,
            pageSize = pageSize
        )
    }
}
