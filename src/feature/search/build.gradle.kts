plugins {
    id("gamehub.android.composeFeature")
}

android {
    namespace = "io.gamehub.feature.search"
}

dependencies {
    implementation(projects.core.navigation)
}
