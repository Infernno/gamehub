package io.gamehub.feature.home

import io.gamehub.data.games.models.Game

sealed class HomeState

data class Default(
    val popularGames: List<Game>,
) : HomeState()

object Loading : HomeState()
