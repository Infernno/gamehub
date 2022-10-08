package io.gamehub.data.games.repository

import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.models.toDomain
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val api: RawgApi,
) : GameRepository {
    override suspend fun getGames(
        page: Int?,
        pageSize: Int?,
        search: String?,
        genres: List<String>?,
        dates: DateRange?,
        ordering: Ordering?
    ): List<Game> {
        return api.getGames(
            page = page,
            pageSize = pageSize,
            search = search,
            genres = genres?.joinToString { "," },
            dates = dates?.toString(),
            ordering = ordering?.code
        ).results.map { dto ->
            dto.toDomain()
        }
    }
}
