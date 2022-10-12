package io.gamehub.data.games.usecase

import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class FindGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {

    suspend fun invoke(
        query: String
    ): List<GameShort> {
        return gameRepository.getGames(
            pageSize = 30,
            search = query
        )
    }
}
