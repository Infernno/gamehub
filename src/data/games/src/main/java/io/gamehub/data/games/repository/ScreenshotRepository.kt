package io.gamehub.data.games.repository

import arrow.core.Option
import io.gamehub.data.games.models.Screenshot

interface ScreenshotRepository {

    suspend fun getScreenshotFor(
        slug: String,
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<Screenshot>>
}
