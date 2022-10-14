package io.gamehub.data.games.repository

import arrow.core.Option
import arrow.core.handleErrorWith
import arrow.core.rightIfNotNull
import io.gamehub.core.database.dao.GenreDao
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.games.mappers.toDomain
import io.gamehub.data.games.mappers.toEntity
import io.gamehub.data.games.models.Genre
import javax.inject.Inject

internal class GenreRepositoryImpl @Inject constructor(
    private val rawgApi: RawgApi,
    private val genreDao: GenreDao
) : GenreRepository {
    override suspend fun getGenres(page: Int?, pageSize: Int?): Option<List<Genre>> {
        return rawgApi.getGenres(
            page = page,
            pageSize = pageSize
        ).map { response ->
            response.results.map { it.toDomain() }
        }.tap { genres ->
            genreDao.insertAll(genres.map { it.toEntity() })
        }.handleErrorWith {
            genreDao.getAll()?.map { it.toDomain() }.rightIfNotNull { it }
        }.orNone()
    }
}
