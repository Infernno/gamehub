package io.gamehub.data.games.usecase

import io.gamehub.data.common.DateRange
import io.gamehub.data.common.Ordering
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.repository.GameRepository
import java.time.LocalDate
import javax.inject.Inject

class GetNewArrivalsUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend fun invoke(
        page: Int? = null,
        pageSize: Int? = null,
    ): List<GameShort> = gameRepository.getGames(
        page = page,
        pageSize = pageSize,
        ordering = Ordering.RELEASED_REVERSED,
        dates = DateRange.single(LocalDate.now().minusMonths(1)),
        metacritic = 50..100
    ).sortedByDescending { it.releaseDate }
}
