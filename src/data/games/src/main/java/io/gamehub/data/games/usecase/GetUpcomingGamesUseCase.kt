package io.gamehub.data.games.usecase

import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class GetUpcomingGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun invoke(
        dates: DateRange,
        page: Int? = null,
        pageSize: Int? = null
    ): List<Game> {
        return gameRepository.getGames(
            dates = dates,
            ordering = Ordering.ADDED_REVERSED,
            page = page,
            pageSize = pageSize
        )
    }
}
