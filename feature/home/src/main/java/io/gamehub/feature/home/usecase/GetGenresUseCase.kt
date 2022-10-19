package io.gamehub.feature.home.usecase

import arrow.core.Option
import io.gamehub.data.games.models.Genre
import io.gamehub.data.games.repository.GenreRepository
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val genreRepository: GenreRepository
) {
    suspend fun invoke(): Option<List<Genre>> {
        return genreRepository.getGenres()
    }
}
