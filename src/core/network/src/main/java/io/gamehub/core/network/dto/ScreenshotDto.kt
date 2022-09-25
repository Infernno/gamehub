package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenshotDto(
    @SerialName("id")
    val id: Int,

    @SerialName("image")
    val imageUrl: String,
)
