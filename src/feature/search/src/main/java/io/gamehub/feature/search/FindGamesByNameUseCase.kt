package io.gamehub.feature.search

import arrow.core.Option
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class FindGamesByNameUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend fun invoke(
        name: String,
    ): Option<List<GameShort>> {
        return gameRepository.getGamesByName(
            name = name,
            pageSize = 30
        )
    }
}
