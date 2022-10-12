plugins {
    id("gamehub.android.composeFeature")
}

android {
    namespace = "io.gamehub.feature.home"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(projects.data.games)
}
