package io.gamehub.data.games.repository

import io.gamehub.data.games.common.Ordering
import io.gamehub.data.games.models.Screenshot

interface ScreenshotRepository {

    suspend fun getScreenshotFor(
        slug: String,
        ordering: Ordering? = null,
        page: Int? = null,
        pageSize: Int? = null,
    ): List<Screenshot>
}
