package io.gamehub.data.games.models

import io.gamehub.core.network.dto.GameShortDto

data class Game(
    val name: String,
    val imageUrl: String,
)

internal fun GameShortDto.toDomain(): Game {
    return Game(
        name,
        backgroundImage
    )
}
