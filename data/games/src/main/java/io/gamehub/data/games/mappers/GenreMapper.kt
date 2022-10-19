package io.gamehub.data.games.mappers

import io.gamehub.core.database.entity.GenreEntity
import io.gamehub.core.network.dto.GenreFullDto
import io.gamehub.data.games.models.Genre

internal fun GenreFullDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
        slug = slug,
        gamesCount = gamesCount,
        imageUrl = imageUrl
    )
}

internal fun GenreEntity.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
        slug = slug,
        gamesCount = gamesCount,
        imageUrl = imageUrl
    )
}

internal fun Genre.toEntity(): GenreEntity {
    return GenreEntity(
        id = id,
        name = name,
        slug = slug,
        gamesCount = gamesCount,
        imageUrl = imageUrl
    )
}
