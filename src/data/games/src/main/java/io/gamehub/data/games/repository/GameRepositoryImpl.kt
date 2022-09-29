package io.gamehub.data.games.repository

import arrow.core.Option
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
        parentPlatform: String?,
        platforms: String?,
        stores: String?,
        developers: String?,
        publishers: String?,
        genres: String?,
        tags: String?,
        creators: String?,
        dates: DateRange?,
        platformsCount: Int?,
        excludeCollection: Int?,
        excludeAdditions: Boolean?,
        excludeParents: Boolean?,
        excludeGameSeries: Boolean?,
        ordering: Ordering?
    ): Option<List<Game>> {
        return api.getGames(
            page = page,
            pageSize = pageSize,
            search = search,
            parentPlatform = parentPlatform,
            platforms = platforms,
            stores = stores,
            developers = developers,
            publishers = publishers,
            genres = genres,
            tags = tags,
            creators = creators,
            dates = dates?.toString(),
            platformsCount = platformsCount,
            excludeCollection = excludeCollection,
            excludeAdditions = excludeAdditions,
            excludeParents = excludeParents,
            excludeGameSeries = excludeGameSeries,
            ordering = ordering?.code
        ).map {
            it.results.map { dto ->
                dto.toDomain()
            }
        }.orNone()
    }
}
