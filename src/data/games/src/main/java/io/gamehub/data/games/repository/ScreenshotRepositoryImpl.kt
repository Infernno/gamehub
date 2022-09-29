package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Screenshot
import io.gamehub.data.games.models.toDomain
import javax.inject.Inject

internal class ScreenshotRepositoryImpl @Inject constructor(
    private val rawgApi: RawgApi
) : ScreenshotRepository {
    override suspend fun getScreenshotFor(
        slug: String,
        ordering: Ordering?,
        page: Int?,
        pageSize: Int?
    ): Option<List<Screenshot>> {
        return rawgApi.getScreenshotsOfTheGame(
            slug = slug,
            ordering = ordering?.code,
            page = page,
            pageSize = pageSize
        ).map { response ->
            response.results.map {
                it.toDomain()
            }
        }.orNone()
    }
}
