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

    @SerialName("metacritic")
    val metacritic: Int?,

    @SerialName("released")
    val released: String?,

    @SerialName("tba")
    val tba: Boolean?,

    @SerialName("background_image")
    val backgroundImageUrl: String?,

    @SerialName("website")
    val websiteUrl: String,

    @SerialName("playtime")
    val playtime: Int?,

    @SerialName("screenshots_count")
    val screenshotsCount: Int?,
)
