package io.gamehub.data.games.usecase

import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun getGameDetails(
        slug: String
    ): GameDetails = gameRepository.getGameDetails(slug)
}
