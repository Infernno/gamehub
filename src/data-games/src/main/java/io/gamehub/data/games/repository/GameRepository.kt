package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.data.games.models.Game

interface GameRepository {
    suspend fun getPopularGames(): Option<List<Game>>

    suspend fun getAllGames(): Option<List<Game>>
}
