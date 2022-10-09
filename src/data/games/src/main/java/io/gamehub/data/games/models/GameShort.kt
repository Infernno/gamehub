package io.gamehub.data.games.models

import io.gamehub.core.network.dto.GameShortDto
import java.time.LocalDate

data class GameShort(
    val name: String,
    val slug: String,
    val genres: List<String>,
    val screenshots: List<String>,
    val imageUrl: String?,
    val releaseDate: LocalDate?
)

internal fun GameShortDto.toDomain(): GameShort {
    return GameShort(
        name,
        slug,
        genres?.map { it.name } ?: emptyList(),
        screenshots?.map { it.imageUrl } ?: emptyList(),
        backgroundImage,
        released?.let {
            LocalDate.parse(it)
        },
    )
}
