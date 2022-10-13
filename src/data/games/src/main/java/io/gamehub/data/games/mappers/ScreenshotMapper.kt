package io.gamehub.data.games.mappers

import io.gamehub.core.network.dto.ScreenshotDto
import io.gamehub.data.games.models.Screenshot

internal fun ScreenshotDto.toDomain(): Screenshot {
    return Screenshot(imageUrl)
}
