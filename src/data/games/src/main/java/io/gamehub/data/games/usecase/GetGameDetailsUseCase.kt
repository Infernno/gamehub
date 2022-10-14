package io.gamehub.data.games.usecase

import arrow.core.Option
import arrow.core.continuations.option
import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.models.Screenshot
import io.gamehub.data.games.repository.GameDetailsRepository
import io.gamehub.data.games.repository.ScreenshotRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gameRepository: GameDetailsRepository,
    private val screenshotRepository: ScreenshotRepository,
) {
    suspend fun invoke(
        slug: String,
    ): Option<Pair<GameDetails, List<Screenshot>>> = option {
        val details = gameRepository.getGameDetails(slug).bind()
        val screenshots = screenshotRepository.getScreenshotFor(slug).bind()

        Pair(details, screenshots)
    }
}
