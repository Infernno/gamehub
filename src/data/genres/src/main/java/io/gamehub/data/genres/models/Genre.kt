package io.gamehub.data.genres.models

import io.gamehub.core.network.dto.GenreFullDto

data class Genre(
    val id: Int,
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageUrl: String
)

internal fun GenreFullDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name,
        slug = slug,
        gamesCount = gamesCount,
        imageUrl = imageUrl
    )
}
