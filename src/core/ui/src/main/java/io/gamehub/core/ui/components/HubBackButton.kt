package io.gamehub.core.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.gamehub.core.ui.theme.GameHubTheme

@Composable
fun HubBackButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = { }
) {
    Icon(
        modifier = modifier
            .size(30.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .clickable { onClick() },
        imageVector = Icons.Default.ArrowBack,
        contentDescription = null
    )
}

@Preview
@Composable
private fun BackButtonPreview() {
    GameHubTheme {
        HubBackButton()
    }
}
