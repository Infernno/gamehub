package io.gamehub.feature.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SearchScreen() {
    Text(text = "Search screen!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SearchScreen()
}
