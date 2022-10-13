package io.gamehub.data.games.mappers

import io.gamehub.core.network.dto.GameShortDto
import io.gamehub.data.games.models.GameShort
import java.time.LocalDate

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
