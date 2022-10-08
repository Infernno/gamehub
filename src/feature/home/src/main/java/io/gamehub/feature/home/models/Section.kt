package io.gamehub.feature.home.models

import androidx.annotation.StringRes
import io.gamehub.data.games.models.Game
import io.gamehub.data.games.models.GameWithScreenshots
import io.gamehub.data.genres.models.Genre

sealed class Section

data class SliderSection(
    val items: List<Game>
) : Section()

data class GenresSection(
    val items: List<Genre>
) : Section()

data class GamesSection(
    @StringRes
    val titleId: Int,
    val items: List<Game>
) : Section()

data class GamesWithScreenshotsSection(
    @StringRes
    val titleId: Int,
    val items: List<GameWithScreenshots>
) : Section()

