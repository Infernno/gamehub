package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.core.network.api.GamesApi
import io.gamehub.data.games.models.Game
import javax.inject.Inject

internal class GameRepositoryImpl @Inject constructor(
    private val api: GamesApi
) : GameRepository {
    override suspend fun getPopularGames(): Option<List<Game>> {
        return getAllGames()
    }

    override suspend fun getAllGames(): Option<List<Game>> {
        return api
            .getAll()
            .map { response ->
                response.results.map { dto ->
                    Game(dto.name, dto.backgroundImage)
                }
            }
            .orNone()
    }

}
