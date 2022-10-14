package io.gamehub.data.games.mappers

import io.gamehub.core.database.entity.GameDetailsEntity
import io.gamehub.core.network.dto.GameDetailsDto
import io.gamehub.data.games.models.GameDetails
import java.time.LocalDate

internal fun GameDetailsDto.toDomain(): GameDetails {
    return GameDetails(
        id = id,
        slug = slug,
        name = name,
        nameOriginal = nameOriginal,
        descriptionRaw = descriptionRaw,
        metacritic = metacritic,
        released = released?.let { LocalDate.parse(it) },
        tba = tba,
        backgroundImageUrl = backgroundImageUrl,
        websiteUrl = websiteUrl,
        playtime = playtime,
        screenshotsCount = screenshotsCount,
        genres = genres?.map { it.name } ?: emptyList(),
        developers = developers?.map { it.name } ?: emptyList(),
        publishers = publishers?.map { it.name } ?: emptyList(),
        stores = stores?.map { it.store.name } ?: emptyList(),
        platforms = platforms?.map { it.platform.name } ?: emptyList()
    )
}

internal fun GameDetailsEntity.toDomain(): GameDetails {
    return GameDetails(
        id = id,
        slug = slug,
        name = name,
        nameOriginal = nameOriginal,
        descriptionRaw = descriptionRaw,
        metacritic = metacritic,
        released = released?.let { LocalDate.parse(it) },
        tba = tba,
        backgroundImageUrl = backgroundImageUrl,
        websiteUrl = websiteUrl,
        playtime = playtime,
        screenshotsCount = screenshotsCount,
        genres = genres,
        developers = developers,
        publishers = publishers,
        stores = stores,
        platforms = platforms
    )
}

internal fun GameDetails.toEntity() : GameDetailsEntity {
    TODO()
}
