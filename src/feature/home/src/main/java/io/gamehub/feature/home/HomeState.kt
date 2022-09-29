package io.gamehub.feature.home

import io.gamehub.data.games.models.Game

sealed class HomeState

data class Default(
    val popularGames: List<Game>,
    val upcomingGames: List<Game>,
) : HomeState()

object Loading : HomeState()

object Error : HomeState()
