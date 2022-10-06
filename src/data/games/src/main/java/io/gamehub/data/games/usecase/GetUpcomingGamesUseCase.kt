package io.gamehub.data.games.usecase

import arrow.core.Option
import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class GetUpcomingGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun getUpcomingGames(
        dates: DateRange,
        page: Int? = null,
        pageSize: Int? = null
    ): Option<List<Game>> {
        return gameRepository.getGames(
            dates = dates,
            ordering = Ordering.ADDED_REVERSED,
            page = page,
            pageSize = pageSize
        ).filter { it.isNotEmpty() }
    }
}
