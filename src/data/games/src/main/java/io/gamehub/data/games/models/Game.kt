package io.gamehub.data.games.models

import io.gamehub.core.network.dto.GameShortDto
import java.time.LocalDate

data class Game(
    val name: String,
    val slug: String,
    val imageUrl: String?,
    val releaseDate: LocalDate?
)

internal fun GameShortDto.toDomain(): Game {
    return Game(
        name,
        slug,
        backgroundImage,
        released?.let {
            LocalDate.parse(it)
        }
    )
}
