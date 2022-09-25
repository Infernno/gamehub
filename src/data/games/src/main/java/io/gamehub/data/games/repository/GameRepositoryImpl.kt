package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.models.toDomain
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val api: RawgApi,
) : GameRepository {
    override suspend fun getPopularGames(): Option<List<Game>> {
        return getAllGames()
    }

    override suspend fun getAllGames(): Option<List<Game>> {
        return api
            .getGames(
                ordering = "-added",
                dates = "2022-9-24, 2022-12-30"
            )
            .map { response ->
                response.results.map { dto ->
                    dto.toDomain()
                }
            }
            .orNone()
    }
}
