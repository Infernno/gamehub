package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.data.games.models.DateRange
import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.models.GameShort

interface GameRepository {

    suspend fun getUpcomingGames(
        dateRange: DateRange,
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<GameShort>>

    suspend fun getNewArrivals(
        dateRange: DateRange,
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<GameShort>>

    suspend fun getPopularGames(
        dateRange: DateRange,
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<GameShort>>

    suspend fun getGamesByGenre(
        genre: String,
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<GameShort>>

    suspend fun getGamesByName(
        name: String,
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<GameShort>>

    suspend fun getGameDetails(
        slug: String,
    ): Option<GameDetails>
}
