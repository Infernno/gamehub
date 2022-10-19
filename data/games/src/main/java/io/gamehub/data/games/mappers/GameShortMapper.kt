package io.gamehub.data.games.mappers

import io.gamehub.core.database.entity.GameShortEntity
import io.gamehub.core.network.dto.GameShortDto
import io.gamehub.data.games.models.GameShort
import java.time.LocalDate

internal fun GameShortDto.toDomain(): GameShort {
    return GameShort(
        id,
        name,
        slug,
        metacritic,
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

internal fun GameShortEntity.toDomain(): GameShort {
    return GameShort(
        id,
        name,
        slug,
        metacritic,
        genres,
        screenshots,
        platforms,
        stores,
        imageUrl,
        releaseDate
    )
}

internal fun GameShort.toEntity(): GameShortEntity {
    return GameShortEntity(
        id,
        name,
        slug,
        metacritic,
        genres,
        screenshots,
        platforms,
        stores,
        imageUrl,
        releaseDate
    )
}
