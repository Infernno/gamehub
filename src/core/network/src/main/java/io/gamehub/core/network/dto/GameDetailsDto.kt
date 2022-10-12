package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameDetailsDto(
    @SerialName("id")
    val id: Int,

    @SerialName("slug")
    val slug: String,

    @SerialName("name")
    val name: String,

    @SerialName("name_original")
    val nameOriginal: String,

    @SerialName("description")
    val description: String,

    @SerialName("description_raw")
    val descriptionRaw: String,

    @SerialName("metacritic")
    val metacritic: Int? = null,

    @SerialName("released")
    val released: String? = null,

    @SerialName("tba")
    val tba: Boolean? = null,

    @SerialName("background_image")
    val backgroundImageUrl: String? = null,

    @SerialName("website")
    val websiteUrl: String? = null,

    @SerialName("playtime")
    val playtime: Int? = null,

    @SerialName("screenshots_count")
    val screenshotsCount: Int? = null,

    @SerialName("genres")
    val genres: List<GenreShortDto>? = null,

    @SerialName("developers")
    val developers: List<GenreShortDto>? = null,

    @SerialName("platforms")
    val platforms: List<BasePlatformDto>? = null
)
