package io.gamehub.data.games.models

import io.gamehub.core.network.dto.GameShortDto
import java.time.LocalDate

data class GameShort(
    val id: Long,
    val name: String,
    val slug: String,
    val metacritic: Int?,
    val genres: List<String>,
    val screenshots: List<String>,
    val platforms: List<String>,
    val stores: List<String>,
    val imageUrl: String?,
    val releaseDate: LocalDate?
)


