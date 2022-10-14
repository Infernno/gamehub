package io.gamehub.feature.releasecalendar

import io.gamehub.data.games.models.DateRange
import io.gamehub.data.games.repository.GameRepository
import java.time.LocalDate
import javax.inject.Inject

class GetUpcomingGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend fun invoke(
        page: Int,
        pageSize: Int,
    ) = gameRepository.getUpcomingGames(
        dateRange = DateRange(
            start = LocalDate.now(),
            endInclusive = LocalDate.now().plusYears(2)
        ),
        page = page,
        pageSize = pageSize
    )
}
