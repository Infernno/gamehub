package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasePlatformDto(

    @SerialName("platform")
    val platform: PlatformDto,
)

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
    val yearEnd: Int? = null,

    @SerialName("year_start")
    val yearStart: Int? = null,

    @SerialName("games_count")
    val gamesCount: Long? = null,

    @SerialName("image_background")
    val imageBackground: String? = null,
)
