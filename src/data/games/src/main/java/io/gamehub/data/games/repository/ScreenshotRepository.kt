package io.gamehub.data.games.repository

import io.gamehub.data.games.models.Screenshot

interface ScreenshotRepository {

    suspend fun getScreenshotFor(
        
    ) : List<Screenshot>
}
