package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.data.games.models.GameDetails

interface GameDetailsRepository {
    suspend fun getGameDetails(
        slug: String,
    ): Option<GameDetails>
}
