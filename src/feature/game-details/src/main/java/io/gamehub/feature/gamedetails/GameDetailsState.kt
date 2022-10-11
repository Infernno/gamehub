package io.gamehub.feature.gamedetails

import io.gamehub.data.games.models.GameDetails
import io.gamehub.data.games.models.Screenshot

sealed class GameDetailsState

object Loading : GameDetailsState()
object Error : GameDetailsState()
data class Default(
    val game: GameDetails, val screenshots: List<Screenshot>
) : GameDetailsState()
