package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenreFullDto(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("slug")
    val slug: String,

    @SerialName("games_count")
    val gamesCount: Int,

    @SerialName("image_background")
    val imageUrl: String,
)
