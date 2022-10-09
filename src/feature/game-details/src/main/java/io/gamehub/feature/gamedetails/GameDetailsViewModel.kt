package io.gamehub.feature.gamedetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.core.navigation.navArgs
import io.gamehub.core.utils.extensions.executeSuspendSafe
import io.gamehub.data.games.usecase.GetGameDetailsUseCase
import io.gamehub.feature.gamedetails.navigation.GameDetailsNavigationDestination
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class GameDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getGameDetailsUseCase: GetGameDetailsUseCase
) : ViewModel(), ContainerHost<GameDetailsState, Nothing> {

    private val args = savedStateHandle.navArgs<String>(GameDetailsNavigationDestination.ArgumentKey)

    override val container = container<GameDetailsState, Nothing>(Loading)

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() = intent {
        executeSuspendSafe {
            getGameDetailsUseCase.getGameDetails(args)
        }.onSuccess {
            reduce {
                Default(it)
            }
        }.onFailure {
            reduce {
                Error
            }
        }
    }
}
