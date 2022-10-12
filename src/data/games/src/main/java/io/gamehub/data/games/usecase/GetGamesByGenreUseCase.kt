package io.gamehub.data.games.usecase

import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class GetGamesByGenreUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {

    suspend fun invoke(
        genre: String,
        page: Int? = null,
        pageSize: Int? = null
    ) : List<GameShort> {
        return gameRepository.getGames(
            genres = listOf(genre),
            page = page,
            pageSize = pageSize
        )
    }
}
