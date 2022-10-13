package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.games.mappers.toDomain
import io.gamehub.data.games.models.Genre
import javax.inject.Inject

internal class GenreRepositoryImpl @Inject constructor(
    private val rawgApi: RawgApi
) : GenreRepository {
    override suspend fun getGenres(page: Int?, pageSize: Int?): Option<List<Genre>> {
        return rawgApi.getGenres(
            page = page,
            pageSize = pageSize
        ).map { response ->
            response.results.map { it.toDomain() }
        }.orNone()
    }
}
