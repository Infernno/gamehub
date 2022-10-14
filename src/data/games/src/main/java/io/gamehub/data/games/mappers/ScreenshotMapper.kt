package io.gamehub.data.games.mappers

import io.gamehub.core.database.entity.ScreenshotEntity
import io.gamehub.core.network.dto.ScreenshotDto
import io.gamehub.data.games.models.Screenshot

internal fun ScreenshotDto.toDomain(): Screenshot {
    return Screenshot(
        id = id,
        imageUrl = imageUrl
    )
}

internal fun ScreenshotEntity.toDomain(): Screenshot {
    return Screenshot(
        id = id,
        imageUrl = imageUrl
    )
}

internal fun Screenshot.toEntity(slug: String): ScreenshotEntity {
    return ScreenshotEntity(
        id = id,
        imageUrl = imageUrl,
        gameSlug = slug
    )
}
