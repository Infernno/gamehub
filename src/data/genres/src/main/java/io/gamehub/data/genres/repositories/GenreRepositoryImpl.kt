package io.gamehub.data.genres.repositories

import arrow.core.Option
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.common.Ordering
import io.gamehub.data.genres.models.Genre
import io.gamehub.data.genres.models.toDomain
import javax.inject.Inject

internal class GenreRepositoryImpl @Inject constructor(
    private val rawgApi: RawgApi
) : GenreRepository {
    override suspend fun getGenres(
        ordering: Ordering,
        page: Int?,
        pageSize: Int?
    ): Option<List<Genre>> {
        return rawgApi.getGenres(
            ordering = ordering.code,
            page = page,
            pageSize = pageSize
        ).map { response ->
            response.results.map {
                it.toDomain()
            }
        }.orNone()
    }
}
