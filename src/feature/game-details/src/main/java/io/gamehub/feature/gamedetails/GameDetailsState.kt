package io.gamehub.feature.gamedetails

import io.gamehub.data.games.models.GameDetails

sealed class GameDetailsState

object Loading : GameDetailsState()
object Error : GameDetailsState()
data class Default(val model: GameDetails): GameDetailsState()
