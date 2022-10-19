package io.gamehub.core.network.dto

enum class Ordering(val key: String) {
    ADDED("added"),
    ADDED_REVERSED("-added"),

    RELEASED("released"),

    METACRITIC_REVERSED("-metacritic"),
}
