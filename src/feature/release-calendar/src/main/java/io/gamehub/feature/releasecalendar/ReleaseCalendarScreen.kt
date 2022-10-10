package io.gamehub.feature.releasecalendar

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ReleaseCalendar() {
    Text(text = "Release calendar screen!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReleaseCalendar()
}
