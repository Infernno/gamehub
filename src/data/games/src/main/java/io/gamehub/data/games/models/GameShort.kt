package io.gamehub.data.games.models

import io.gamehub.core.network.dto.GameShortDto
import java.time.LocalDate

data class GameShort(
    val id: Long,
    val name: String,
    val slug: String,
    val genres: List<String>,
    val screenshots: List<String>,
    val platforms: List<String>,
    val stores: List<String>,
    val imageUrl: String?,
    val releaseDate: LocalDate?
)

internal fun GameShortDto.toDomain(): GameShort {
    return GameShort(
        id,
        name,
        slug,
        genres?.map { it.name } ?: emptyList(),
        screenshots?.map { it.imageUrl } ?: emptyList(),
        platforms?.map { it.platform.name } ?: emptyList(),
        stores?.map { it.store.name } ?: emptyList(),
        backgroundImage,
        released?.let {
            LocalDate.parse(it)
        },
    )
}
