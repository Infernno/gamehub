package io.gamehub.data.games.common

enum class Ordering(val code: String) {
    ADDED("added"),
    ADDED_REVERSED("-added"),
    RELEASED_REVERSED("-released"),
    METACRITIC_REVERSED("-metacritic"),
}
