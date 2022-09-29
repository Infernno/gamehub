package io.gamehub.data.games.usecase

import arrow.core.Option
import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.repository.GameRepository
import java.time.LocalDate
import javax.inject.Inject

class GetPopularGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun invoke(
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<Game>> {
        val start = LocalDate.now()
        val end = start.plusYears(1)

        return gameRepository.getGames(
            page = page,
            pageSize = pageSize,
            ordering = Ordering.ADDED_REVERSED,
            dates = DateRange(start, end)
        ).filter { it.isNotEmpty() }
    }
}