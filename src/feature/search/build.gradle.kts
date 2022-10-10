plugins {
    id("gamehub.android.composeFeature")
}

android {
    namespace = "io.gamehub.feature.search"
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.utils)
    implementation(projects.core.navigation)

    implementation(projects.data.games)
    implementation(projects.data.genres)
}
