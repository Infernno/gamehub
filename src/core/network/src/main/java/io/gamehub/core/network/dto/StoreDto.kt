package io.gamehub.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BaseStoreDto(
    @SerialName("store")
    val store: StoreDto,
)

@Serializable
data class StoreDto(
    @SerialName("id")
    val id: Int,

    @SerialName("slug")
    val slug: String,

    @SerialName("name")
    val name: String,
)
