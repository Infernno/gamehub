package io.gamehub.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.data.games.repository.GameRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val gameRepository: GameRepository,
) : ViewModel() {

    val state = flow {
        gameRepository
            .getAllGames()
            .filter { it.isNotEmpty() }
            .tap {
                emit(Default(it))
            }
            .tapNone {
                emit(Loading)
            }
    }.stateIn(viewModelScope, SharingStarted.Lazily, Loading)
}
