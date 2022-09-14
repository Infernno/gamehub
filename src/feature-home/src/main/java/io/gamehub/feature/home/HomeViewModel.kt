package io.gamehub.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.repository.GameRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameRepository: GameRepository
) : ViewModel() {

    val state = flow {
        gameRepository
            .getAllGames()
            .tap {
                emit(State.Default(it))
            }
            .tapNone {
                emit(State.Loading)
            }
    }.stateIn(viewModelScope, SharingStarted.Lazily, State.Loading)

    sealed class State {
        data class Default(
            val popularGames: List<Game>
        ): State()

        object Loading: State()
    }
}
