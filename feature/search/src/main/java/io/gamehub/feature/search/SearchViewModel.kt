package io.gamehub.feature.search

import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.core.common.MviViewModel
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val findGamesUseCase: FindGamesByNameUseCase,
) : MviViewModel<SearchState, Nothing>(initialState = Default) {

    fun search(query: String) = intent {
        reduce {
            Loading
        }

        val result: SearchState = findGamesUseCase.invoke(
            name = query
        ).fold(
            ifEmpty = { Error },
            ifSome = {
                if (it.isEmpty()) NotFound
                else Found(it)
            }
        )

        reduce {
            result
        }
    }
}
