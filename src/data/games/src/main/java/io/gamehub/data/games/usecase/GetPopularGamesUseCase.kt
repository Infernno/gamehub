package io.gamehub.data.games.usecase

import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun invoke(
        page: Int? = null,
        pageSize: Int? = null,
        dates: DateRange? = null
    ): List<GameShort> {
        return gameRepository.getGames(
            page = page,
            pageSize = pageSize,
            ordering = Ordering.METACRITIC_REVERSED,
            dates = dates
        )
    }
}
