package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Game

interface GameRepository {
    suspend fun getGames(
        page: Int? = null,
        pageSize: Int? = null,
        search: String? = null,
        parentPlatform: String? = null,
        platforms: String? = null,
        stores: String? = null,
        developers: String? = null,
        publishers: String? = null,
        genres: String? = null,
        tags: String? = null,
        creators: String? = null,
        dates: DateRange? = null,
        platformsCount: Int? = null,
        excludeCollection: Int? = null,
        excludeAdditions: Boolean? = null,
        excludeParents: Boolean? = null,
        excludeGameSeries: Boolean? = null,
        ordering: Ordering? = null,
    ): Option<List<Game>>
}
