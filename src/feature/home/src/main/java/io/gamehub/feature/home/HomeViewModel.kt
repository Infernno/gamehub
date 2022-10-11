package io.gamehub.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.gamehub.core.utils.extensions.executeSuspendAsyncSafe
import io.gamehub.data.common.DateRange
import io.gamehub.data.games.usecase.GetNewArrivalsUseCase
import io.gamehub.data.games.usecase.GetPopularGamesUseCase
import io.gamehub.data.games.usecase.GetUpcomingGamesUseCase
import io.gamehub.data.genres.usecase.GetGenresUseCase
import io.gamehub.feature.home.models.CategoriesSection
import io.gamehub.feature.home.models.GamesSection
import io.gamehub.feature.home.models.SliderSection
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import java.time.LocalDate
import java.time.temporal.TemporalAdjusters
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val popularGamesUseCase: GetPopularGamesUseCase,
    private val upcomingGamesUseCase: GetUpcomingGamesUseCase,
    private val getNewArrivalsUseCase: GetNewArrivalsUseCase,
    private val genresUseCase: GetGenresUseCase
) : ViewModel(), ContainerHost<HomeState, Nothing> {

    override val container = container<HomeState, Nothing>(Loading)

    init {
        viewModelScope.launch {
            load()
        }
    }

    private suspend fun load() = intent {
        executeSuspendAsyncSafe {

            val bestThisYearTask = async {
                val start = LocalDate.now().withDayOfYear(1)
                val end = start.with(TemporalAdjusters.lastDayOfYear())

                popularGamesUseCase.invoke(dates = DateRange(start, end))
            }

            val genresTask = async { genresUseCase.getGenres() }

            val upcomingTask = async {
                val start = LocalDate.now()
                val end = start.plusMonths(1)

                upcomingGamesUseCase.invoke(dates = DateRange(start, end))
            }

            val recentReleasesTask = async { getNewArrivalsUseCase.invoke() }

            awaitAll(bestThisYearTask, genresTask, recentReleasesTask, recentReleasesTask)

            val bestOfThisYear = bestThisYearTask.await()
            val genres = genresTask.await()
            val comingThisMonth = upcomingTask.await()
            val newGames = recentReleasesTask.await()

            Default(
                listOf(
                    SliderSection(bestOfThisYear),
                    CategoriesSection(
                        titleId = R.string.genres,
                        items = genres
                    ),
                    GamesSection(
                        titleId = R.string.coming_in_a_month,
                        items = comingThisMonth
                    ),
                    GamesSection(
                        titleId = R.string.new_arrivals,
                        items = newGames
                    ),
                )
            )
        }.onSuccess { state ->
            reduce {
                state
            }
        }.onFailure {
            println(it)
            reduce {
                Error
            }
        }
    }
}
