package io.gamehub.data.games.models

data class Genre(
    val id: Int,
    val name: String,
    val slug: String,
    val gamesCount: Int,
    val imageUrl: String
)
