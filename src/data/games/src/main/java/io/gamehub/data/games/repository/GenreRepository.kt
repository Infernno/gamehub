package io.gamehub.data.games.repository

import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Genre

interface GenreRepository {
    suspend fun getGenres(
        ordering: Ordering,
        page: Int? = null,
        pageSize: Int? = null,
    ): List<Genre>
}
