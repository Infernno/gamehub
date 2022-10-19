package io.gamehub.data.games.repository

import arrow.core.Option
import arrow.core.handleErrorWith
import arrow.core.rightIfNotNull
import io.gamehub.core.database.dao.ScreenshotDao
import io.gamehub.core.network.api.RawgApi
import io.gamehub.data.games.mappers.toDomain
import io.gamehub.data.games.mappers.toEntity
import io.gamehub.data.games.models.Screenshot
import javax.inject.Inject

internal class ScreenshotRepositoryImpl @Inject constructor(
    private val rawgApi: RawgApi,
    private val screenshotDao: ScreenshotDao
) : ScreenshotRepository {
    override suspend fun getScreenshotFor(
        slug: String,
        page: Int?,
        pageSize: Int?,
    ): Option<List<Screenshot>> {
        return rawgApi.getScreenshotsOfTheGame(
            slug = slug,
            page = page,
            pageSize = pageSize
        ).map { response ->
            response.results.map { it.toDomain() }
        }.tap { screenshots ->
            screenshotDao.insertAll(
               screenshots.map { it.toEntity(slug) }
            )
        }.handleErrorWith {
            screenshotDao.getForGames(slug)?.map {
                it.toDomain()
            }.rightIfNotNull { it }
        }.orNone()
    }
}
