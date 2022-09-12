package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlatformDto(
    @SerialName("id")
    val id: Long,

    @SerialName("name")
    val name: String,

    @SerialName("slug")
    val slug: String,

    @SerialName("image")
    val image: String? = null,

    @SerialName("year_end")
    val yearEnd: String? = null,

    @SerialName("year_start")
    val yearStart: String? = null,

    @SerialName("games_count")
    val gamesCount: Long,

    @SerialName("image_background")
    val imageBackground: String
)
