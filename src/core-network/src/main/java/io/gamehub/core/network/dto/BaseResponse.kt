package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    @SerialName("count")
    val count: Long,

    @SerialName("next")
    val nextUrl: String?,

    @SerialName("previous")
    val previousUrl: String?,

    @SerialName("results")
    val results: List<T>
)
