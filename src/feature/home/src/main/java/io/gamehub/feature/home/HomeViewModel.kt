package io.gamehub.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.data.games.usecase.GetPopularGamesUseCase
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

        val results = awaitAll(popularTask, upcomingTask)

        if (results.any { it.isEmpty() }) {
            stateFlow.update {
                Error
            }
        } else {
            val popularGames = popularTask.await().orNull()!!

            stateFlow.update {
                Default(
                    popularGames = popularGames,
                    upcomingGames = popularGames
                )
            }
        }
    }
}
