package io.gamehub.feature.gamedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.core.common.MviViewModel
import io.gamehub.core.common.utils.navArgs
import io.gamehub.data.games.usecase.GetGameDetailsUseCase
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGameDetailsUseCase: GetGameDetailsUseCase,
) : MviViewModel<GameDetailsState, Nothing>(initialState = Loading) {

    private val args = savedStateHandle.navArgs<String>(DETAILS_ARG_KEY)

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() = intent {
        val state : GameDetailsState = getGameDetailsUseCase.invoke(args).fold(
            ifEmpty = { Error },
            ifSome = { Default(it.first, it.second) }
        )

        reduce {
            state
        }
    }
}
