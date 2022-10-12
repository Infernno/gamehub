package io.gamehub.data.games.repository

import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.common.DateRange
import io.gamehub.data.games.common.Ordering
import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.models.GameShort
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
        ordering: Ordering?,
        metacritic: IntRange?
    ): List<GameShort> {
        return api.getGames(
            page = page,
            pageSize = pageSize,
            search = search,
            genres = genres?.joinToString { "," },
            dates = dates?.toString(),
            ordering = ordering?.code,
            metacritic = metacritic?.convert()
        ).results.map { dto ->
            dto.toDomain()
        }
    }

    override suspend fun getGameDetails(id: Int): GameDetails {
        return api.getGameDetails(id.toString()).toDomain()
    }

    override suspend fun getGameDetails(slug: String): GameDetails {
        return api.getGameDetails(slug).toDomain()
    }

    private fun IntRange.convert(): String {
        return if (first == last) first.toString()
        else "$first, $last"
    }
}
