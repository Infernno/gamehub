plugins {
    id("gamehub.android.composeFeature")
}

android {
    namespace = "io.gamehub.feature.releasecalendar"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.navigation)
    implementation(projects.data.games)
}
