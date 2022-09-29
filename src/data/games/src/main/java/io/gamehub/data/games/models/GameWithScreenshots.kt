package io.gamehub.data.games.models

data class GameWithScreenshots(
    val game: Game,
    val screenshots: List<Screenshot>
)
