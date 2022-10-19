package io.gamehub.feature.home

import io.gamehub.feature.home.models.Section

sealed class HomeState

data class Default(val sections: List<Section>) : HomeState()
object Loading : HomeState()
object Error : HomeState()
