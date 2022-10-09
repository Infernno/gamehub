package io.gamehub.feature.home.models

import androidx.annotation.StringRes
import io.gamehub.data.games.models.GameShort
import io.gamehub.data.genres.models.Genre

sealed class Section

data class SliderSection(
    val items: List<GameShort>
) : Section()

data class CategoriesSection(
    @StringRes
    val titleId: Int,
    @StringRes
    val subtitleId: Int? = null,
    val items: List<Genre>
) : Section()

data class GamesSection(
    @StringRes
    val titleId: Int,
    @StringRes
    val subtitleId: Int? = null,
    val items: List<GameShort>
) : Section()
