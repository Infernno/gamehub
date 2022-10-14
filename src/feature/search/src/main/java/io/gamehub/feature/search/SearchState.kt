package io.gamehub.feature.search

import io.gamehub.data.games.models.GameShort

sealed class SearchState

object Default : SearchState()
object Loading : SearchState()
object Error : SearchState()

data class Found(val items: List<GameShort>) : SearchState()
object NotFound : SearchState()
