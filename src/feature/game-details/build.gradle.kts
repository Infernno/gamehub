plugins {
    id("gamehub.android.composeFeature")
}

android {
    namespace = "io.gamehub.feature.gamedetails"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(projects.data.games)
}
