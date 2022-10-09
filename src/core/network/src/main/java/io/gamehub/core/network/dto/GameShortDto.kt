package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameShortDto(
    @SerialName("id")
    val id: Long,

    @SerialName("slug")
    val slug: String,

    @SerialName("name")
    val name: String,

    @SerialName("released")
    val released: String?,

    @SerialName("tba")
    val tba: Boolean?,

    @SerialName("background_image")
    val backgroundImage: String?,

    @SerialName("genres")
    val genres: List<GenreShortDto>?,

    @SerialName("short_screenshots")
    val screenshots: List<ScreenshotShortDto>?,
)
