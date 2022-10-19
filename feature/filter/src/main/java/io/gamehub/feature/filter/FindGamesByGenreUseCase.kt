package io.gamehub.feature.filter

import arrow.core.Option
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.games.repository.GameRepository
import javax.inject.Inject

class FindGamesByGenreUseCase @Inject constructor(
    private val gameRepository: GameRepository,
) {
    suspend fun invoke(
        genre: String,
        page: Int? = null,
        pageSize: Int? = null,
    ): Option<List<GameShort>> {
        return gameRepository.getGamesByGenre(
            genre, page, pageSize
        )
    }
}
