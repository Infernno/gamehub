package io.gamehub.data.games.models

import io.gamehub.core.network.dto.GameDetailsDto

data class GameDetails(
    val id: Int,
    val slug: String,
    val name: String,
    val nameOriginal: String,
    val description: String,
    val metacritic: Int?,
    val released: String?,
    val tba: Boolean?,
    val backgroundImageUrl: String?,
    val websiteUrl: String?,
    val playtime: Int?,
    val screenshotsCount: Int?,
    val genres: List<String>,
    val developers: List<String>,
    val platforms: List<String>,
)

internal fun GameDetailsDto.toDomain(): GameDetails {
    return GameDetails(
        id = id,
        slug = slug,
        name = name,
        nameOriginal = nameOriginal,
        description = descriptionRaw,
        metacritic = metacritic,
        released = released,
        tba = tba,
        backgroundImageUrl = backgroundImageUrl,
        websiteUrl = websiteUrl,
        playtime = playtime,
        screenshotsCount = screenshotsCount,
        genres = genres?.map { it.name } ?: emptyList(),
        developers = developers?.map { it.name } ?: emptyList(),
        platforms = platforms?.map { it.platform.name } ?: emptyList()
    )
}
