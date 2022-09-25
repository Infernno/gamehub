package io.gamehub.data.games.models

import io.gamehub.core.network.dto.ScreenshotDto

data class Screenshot(
    val imageUrl: String,
)

internal fun ScreenshotDto.toDomain(): Screenshot {
    return Screenshot(imageUrl)
}
