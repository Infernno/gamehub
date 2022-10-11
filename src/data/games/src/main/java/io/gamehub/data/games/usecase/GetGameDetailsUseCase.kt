package io.gamehub.data.games.usecase

import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.models.Screenshot
import io.gamehub.data.games.repository.GameRepository
import io.gamehub.data.games.repository.ScreenshotRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val screenshotRepository: ScreenshotRepository
) {
    suspend fun getGameDetails(
        slug: String
    ): Pair<GameDetails, List<Screenshot>> = coroutineScope {
        val details = async { gameRepository.getGameDetails(slug) }
        val screenshots = async { screenshotRepository.getScreenshotFor(slug) }

        awaitAll(details, screenshots)

        Pair(details.await(), screenshots.await())
    }
}
