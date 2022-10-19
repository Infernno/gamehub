package io.gamehub.feature.home

import androidx.lifecycle.viewModelScope
import arrow.core.Option
import arrow.core.continuations.option
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.core.common.MviViewModel
import io.gamehub.feature.home.models.CategoriesSection
import io.gamehub.feature.home.models.GamesSection
import io.gamehub.feature.home.models.SliderSection
import io.gamehub.feature.home.usecase.BestOfTheYearGamesUseCase
import io.gamehub.feature.home.usecase.GetGenresUseCase
import io.gamehub.feature.home.usecase.MostPopularGamesUseCase
import io.gamehub.feature.home.usecase.NewArrivalsUseCase
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val bestOfTheYearGamesUseCase: BestOfTheYearGamesUseCase,
    private val genresUseCase: GetGenresUseCase,
    private val mostPopularGamesUseCase: MostPopularGamesUseCase,
    private val newArrivalsUseCase: NewArrivalsUseCase,
) : MviViewModel<HomeState, Nothing>(initialState = Loading) {

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() = intent {
        val state = getState().fold(
            ifEmpty = { Error },
            ifSome = { it }
        )

        reduce {
            state
        }
    }

    private suspend fun getState(): Option<Default> = option {
        val bestOfTheYear = bestOfTheYearGamesUseCase.invoke().bind()
        val genres = genresUseCase.invoke().bind()
        val mostPopularGames = mostPopularGamesUseCase.invoke().bind()
        val newArrivals = newArrivalsUseCase.invoke().bind()

        Default(
            listOf(
                SliderSection(bestOfTheYear),
                CategoriesSection(
                    titleId = R.string.genres,
                    items = genres
                ),
                GamesSection(
                    titleId = R.string.most_popular_games,
                    items = mostPopularGames
                ),
                GamesSection(
                    titleId = R.string.new_arrivals,
                    items = newArrivals
                ),
            )
        )
    }
}
