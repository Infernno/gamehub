package io.gamehub.data.games.mappers

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
