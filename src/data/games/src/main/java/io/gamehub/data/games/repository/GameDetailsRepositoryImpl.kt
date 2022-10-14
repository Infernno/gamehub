package io.gamehub.data.games.repository

import arrow.core.Option
import arrow.core.toOption
import io.gamehub.core.database.dao.GameDetailsDao
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.games.mappers.toDomain
import io.gamehub.data.games.models.GameDetails

internal class GameDetailsRepositoryImpl constructor(
    private val api: RawgApi,
    private val dao: GameDetailsDao,
) : GameDetailsRepository {
    override suspend fun getGameDetails(slug: String): Option<GameDetails> {
        val result = api.getGameDetails(
            id = slug
        ).map {
            it.toDomain()
        }.tap {
            dao.insert(it)
        }

            .bimap(
            leftOperation = { dao.getBySlug(slug)?.toDomain() },
            rightOperation = { it.toDomain() }
        )

        return result.orNone()
    }
}
