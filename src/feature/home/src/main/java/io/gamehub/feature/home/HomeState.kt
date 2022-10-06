package io.gamehub.feature.home

import io.gamehub.data.games.models.Game
import io.gamehub.data.genres.models.Genre

sealed class HomeState

data class Default(
    val popularGames: List<Game>,
    val upcomingGames: List<Game>,
    val genres: List<Genre>
) : HomeState()

object Loading : HomeState()

object Error : HomeState()
