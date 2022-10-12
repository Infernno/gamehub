plugins {
    id("gamehub.android.composeFeature")
}

android {
    namespace = "io.gamehub.feature.home"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.utils)
    implementation(projects.core.navigation)

    implementation(projects.data.games)
}
