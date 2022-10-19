package io.gamehub.data.games.repository

import arrow.core.Either
import arrow.core.Option
import arrow.core.handleErrorWith
import arrow.core.rightIfNotNull
import arrow.retrofit.adapter.either.networkhandling.CallError
import io.gamehub.core.database.dao.GameShortsDao
import io.gamehub.core.database.entity.GameShortEntity
import io.gamehub.core.network.api.RawgApi
import io.gamehub.core.network.dto.BaseResponse
import io.gamehub.core.network.dto.GameShortDto
import io.gamehub.core.network.dto.Ordering
import io.gamehub.data.games.mappers.toDomain
import io.gamehub.data.games.mappers.toEntity
import io.gamehub.data.games.models.DateRange
import io.gamehub.data.games.models.GameShort
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val api: RawgApi,
    private val gameShortsDao: GameShortsDao
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
        ).sync {
            gameShortsDao.getGamesByDate(
                start = dateRange.startDateAsTimeStamp(),
                end = dateRange.endDateAsTimeStamp(),
                pageSize = getPageSize(pageSize),
                offset = getOffset(page, pageSize)
            )
        }
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
        ).sync {
            gameShortsDao.getGamesByDate(
                start = dateRange.startDateAsTimeStamp(),
                end = dateRange.endDateAsTimeStamp(),
                pageSize = getPageSize(pageSize),
                offset = getOffset(page, pageSize)
            )
        }
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
        ).sync {
            gameShortsDao.getPopularGames(
                start = dateRange.startDateAsTimeStamp(),
                end = dateRange.endDateAsTimeStamp(),
                pageSize = getPageSize(pageSize),
                offset = getOffset(page, pageSize)
            )
        }
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
        ).sync {
            gameShortsDao.getGamesByGenre(
                genre = genre,
                pageSize = getPageSize(pageSize),
                offset = getOffset(page, pageSize)
            )
        }
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
        ).sync {
            gameShortsDao.getGamesByName(
                title = name,
                pageSize = getPageSize(pageSize),
                offset = getOffset(page, pageSize)
            )
        }
    }

    private fun getPageSize(pageSize: Int?): Int {
        return pageSize ?: 30
    }

    private fun getOffset(page: Int?, pageSize: Int?): Int {
        return (page?.minus(1) ?: 0) * getPageSize(pageSize)
    }

    private suspend fun Either<CallError, BaseResponse<GameShortDto>>.sync(
        fallback: suspend () -> List<GameShortEntity>?,
    ): Option<List<GameShort>> {
        return map { response ->
            response.results.map { it.toDomain() }
        }.tap { games ->
            gameShortsDao.insert(games.map { it.toEntity() })
        }.handleErrorWith {
            fallback()?.map { it.toDomain() }.rightIfNotNull { it }
        }.orNone()
    }
}
