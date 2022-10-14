package io.gamehub.core.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

object Dimens {
    val horizontalScreenPadding =  20.dp
    val horizontalScreenPaddings = PaddingValues(horizontal = horizontalScreenPadding)
}

fun Modifier.horizontalScreenPaddings(): Modifier {
    return padding(Dimens.horizontalScreenPaddings)
}
