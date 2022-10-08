package io.gamehub.data.games.usecase

import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun invoke(
        page: Int? = null,
        pageSize: Int? = null,
        dates: DateRange? = null
    ): List<Game> {
        return gameRepository.getGames(
            page = page,
            pageSize = pageSize,
            ordering = Ordering.METACRITIC_REVERSED,
            dates = dates
        )
    }
}
