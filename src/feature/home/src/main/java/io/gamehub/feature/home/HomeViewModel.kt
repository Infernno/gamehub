package io.gamehub.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.None
import arrow.core.combine
import arrow.typeclasses.Monoid.Companion.either
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.data.games.usecase.GetPopularGamesUseCase
import io.gamehub.data.genres.usecase.GetGenresUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularGamesUseCase: GetPopularGamesUseCase,
    private val genresUseCase: GetGenresUseCase
) : ViewModel() {

    private val stateFlow = MutableStateFlow<HomeState>(Loading)
    val state = stateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() = coroutineScope {
        val popularTask = async { popularGamesUseCase.invoke() }
        val upcomingTask = async { popularGamesUseCase.invoke() }
        val genresTask = async { genresUseCase.getGenres() }

        val results = awaitAll(popularTask, upcomingTask, genresTask)

        if (results.any { it is None }) {
            stateFlow.update {
                Error
            }
        } else {
            val popularGames = popularTask.await().orNull()!!
            val genres = genresTask.await().orNull()!!

            stateFlow.update {
                Default(
                    popularGames = popularGames,
                    upcomingGames = popularGames,
                    genres = genres
                )
            }
        }
    }
}
