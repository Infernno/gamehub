package io.gamehub.ui

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    @StringRes
    val titleId: Int,
    val icon: ImageVector,
    val route: String,
)
