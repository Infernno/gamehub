plugins {
    id("gamehub.android.composeFeature")
}

android {
    namespace = "io.gamehub.feature.home"
}

dependencies {
    implementation(libs.bundles.arrowKt)

    implementation(projects.core.ui)
    implementation(projects.core.navigation)

    implementation(projects.data.games)
    implementation(projects.data.genres)
}
