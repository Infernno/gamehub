package io.gamehub.data.games.repository

import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Genre
import io.gamehub.data.games.models.toDomain
import javax.inject.Inject

internal class GenreRepositoryImpl @Inject constructor(
    private val rawgApi: RawgApi
) : GenreRepository {
    override suspend fun getGenres(
        ordering: Ordering,
        page: Int?,
        pageSize: Int?
    ): List<Genre> {
        return rawgApi.getGenres(
            ordering = ordering.code,
            page = page,
            pageSize = pageSize
        ).results.map { dto ->
            dto.toDomain()
        }
    }
}
