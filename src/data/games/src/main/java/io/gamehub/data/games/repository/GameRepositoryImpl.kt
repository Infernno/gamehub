package io.gamehub.data.games.repository

import arrow.core.Either
import arrow.core.Option
import arrow.retrofit.adapter.either.networkhandling.CallError
import io.gamehub.core.network.api.RawgApi
import io.gamehub.core.network.dto.BaseResponse
import io.gamehub.core.network.dto.GameShortDto
import io.gamehub.core.network.dto.Ordering
import io.gamehub.data.games.mappers.toDomain
import io.gamehub.data.games.models.DateRange
import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.models.GameShort
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val api: RawgApi,
) : GameRepository {

    override suspend fun getUpcomingGames(
        dateRange: DateRange,
        page: Int?,
        pageSize: Int?,
    ): Option<List<GameShort>> {
        return api.getGames(
            ordering = Ordering.RELEASED.key,
            dates = dateRange.toString(),
            page = page,
            pageSize = pageSize
        ).mapToDomain()
    }

    override suspend fun getNewArrivals(
        dateRange: DateRange,
        page: Int?,
        pageSize: Int?,
    ): Option<List<GameShort>> {
        return api.getGames(
            dates = dateRange.toString(),
            ordering = Ordering.ADDED_REVERSED.key,
            page = page,
            pageSize = pageSize,
        ).mapToDomain()
    }

    override suspend fun getPopularGames(
        dateRange: DateRange,
        page: Int?,
        pageSize: Int?,
    ): Option<List<GameShort>> {
        return api.getGames(
            dates = dateRange.toString(),
            ordering = Ordering.METACRITIC_REVERSED.key,
            page = page,
            pageSize = pageSize,
        ).mapToDomain()
    }

    override suspend fun getGamesByGenre(
        genre: String,
        page: Int?,
        pageSize: Int?,
    ): Option<List<GameShort>> {
        return api.getGames(
            ordering = Ordering.METACRITIC_REVERSED.key,
            genres = genre,
            page = page,
            pageSize = pageSize,
        ).mapToDomain()
    }

    override suspend fun getGamesByName(
        name: String,
        page: Int?,
        pageSize: Int?,
    ): Option<List<GameShort>> {
        return api.getGames(
            search = name,
            page = page,
            pageSize = pageSize
        ).mapToDomain()
    }

    private fun Either<CallError, BaseResponse<GameShortDto>>.mapToDomain(): Option<List<GameShort>> {
        return map { response ->
            response.results.map { it.toDomain() }
        }.orNone()
    }
}
