package io.gamehub.data.games.usecase

import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.GameWithScreenshots
import io.gamehub.data.games.repository.GameRepository
import io.gamehub.data.games.repository.ScreenshotRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class GetNewGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository,
    private val screenshotRepository: ScreenshotRepository
) {
    suspend fun invoke(
        page: Int? = null,
        pageSize: Int? = null,
    ): List<GameWithScreenshots> = coroutineScope {

        val games = gameRepository.getGames(
            page = page,
            pageSize = pageSize,
            ordering = Ordering.ADDED
        )

        games.map {
            async {
                val job = screenshotRepository.getScreenshotFor(
                    slug = it.slug
                )

                Pair(it, job)
            }
        }
            .awaitAll()
            .filter { it.second.isNotEmpty() }
            .map {
                GameWithScreenshots(it.first, it.second)
            }
    }
}
