package io.gamehub.data.games.repository

import arrow.core.Option
import arrow.core.handleErrorWith
import arrow.core.rightIfNotNull
import io.gamehub.core.database.dao.GameDetailsDao
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.games.mappers.toDomain
import io.gamehub.data.games.mappers.toEntity
import io.gamehub.data.games.models.GameDetails
import javax.inject.Inject

internal class GameDetailsRepositoryImpl @Inject constructor(
    private val api: RawgApi,
    private val dao: GameDetailsDao,
) : GameDetailsRepository {
    override suspend fun getGameDetails(slug: String): Option<GameDetails> {
        val result = api.getGameDetails(
            id = slug
        ).map {
            it.toDomain()
        }.tap {
            dao.insert(it.toEntity())
        }.handleErrorWith {
            dao.getBySlug(slug)?.toDomain().rightIfNotNull { it }
        }

        return result.orNone()
    }
}
