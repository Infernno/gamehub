package io.gamehub.feature.search

import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.core.common.MviViewModel
import io.gamehub.core.common.utils.executeSuspendSafe
import io.gamehub.data.games.usecase.FindGamesUseCase
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val findGamesUseCase: FindGamesUseCase,
) : MviViewModel<SearchState, Nothing>(initialState = Default) {

    fun search(query: String) = intent {
        reduce {
            Loading
        }

        val result = executeSuspendSafe {
            findGamesUseCase.invoke(query)
        }

        result.onSuccess {
            reduce {
                if (it.isEmpty()) NotFound
                else Found(it)
            }
        }.onFailure {
            reduce {
                Error
            }
        }
    }
}
