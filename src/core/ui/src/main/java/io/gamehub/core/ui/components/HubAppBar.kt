package io.gamehub.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.gamehub.core.ui.theme.horizontalScreenPaddings

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HubAppBar(
    modifier: Modifier = Modifier,
    title: String,
    showBackButton: Boolean = false,
    onBackButtonClicked: (() -> Unit)? = null
) {
    CenterAlignedTopAppBar(
        modifier = modifier.horizontalScreenPaddings(),
        title = { Text(text = title) },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = { onBackButtonClicked?.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    )
}
