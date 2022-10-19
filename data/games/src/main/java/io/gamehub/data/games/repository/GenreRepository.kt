package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.data.games.models.Genre

interface GenreRepository {
    suspend fun getGenres(
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<Genre>>
}
