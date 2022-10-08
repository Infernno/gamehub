package io.gamehub.data.genres.repositories

import io.gamehub.data.common.Ordering
import io.gamehub.data.genres.models.Genre

interface GenreRepository {
    suspend fun getGenres(
        ordering: Ordering,
        page: Int? = null,
        pageSize: Int? = null,
    ): List<Genre>
}
