package io.gamehub.data.games.repository

import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.models.GameShort

interface GameRepository {

    suspend fun getGames(
        page: Int? = null,
        pageSize: Int? = null,
        search: String? = null,
        genres: List<String>? = null,
        dates: DateRange? = null,
        ordering: Ordering? = null,
        metacritic: IntRange? = null
    ): List<GameShort>

    suspend fun getGameDetails(
        id: Int
    ): GameDetails

    suspend fun getGameDetails(
        slug: String
    ): GameDetails
}
