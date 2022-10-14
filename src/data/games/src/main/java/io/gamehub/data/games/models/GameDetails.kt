package io.gamehub.data.games.models

import java.time.LocalDate

data class GameDetails(
    val id: Int,
    val slug: String,
    val name: String,
    val nameOriginal: String,
    val description: String,
    val metacritic: Int?,
    val released: LocalDate?,
    val tba: Boolean?,
    val backgroundImageUrl: String?,
    val websiteUrl: String?,
    val playtime: Int?,
    val screenshotsCount: Int?,
    val genres: List<String>,
    val developers: List<String>,
    val publishers: List<String>,
    val stores: List<String>,
    val platforms: List<String>,
)
