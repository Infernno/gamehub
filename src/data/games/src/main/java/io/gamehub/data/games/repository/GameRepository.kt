package io.gamehub.data.games.repository

import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Game

interface GameRepository {

    suspend fun getGames(
        page: Int? = null,
        pageSize: Int? = null,
        search: String? = null,
        genres: List<String>? = null,
        dates: DateRange? = null,
        ordering: Ordering? = null,
    ): List<Game>
}
