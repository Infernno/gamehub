package io.gamehub.feature.home.usecase

import arrow.core.Option
import io.gamehub.data.games.models.DateRange
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.repository.GameRepository
import java.time.LocalDate
import javax.inject.Inject

class BestOfTheYearGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun invoke(): Option<List<GameShort>> = gameRepository.getPopularGames(
        dateRange = DateRange(LocalDate.now().minusYears(1), LocalDate.now()),
        pageSize = 30
    )
}
