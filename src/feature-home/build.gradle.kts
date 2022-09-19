plugins {
    id("gamehub.android.composeLibrary")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.gamehub.feature.home"
}

dependencies {
    implementation(libs.arrow.core)
    implementation(projects.coreUi)
    implementation(projects.dataGames)
}
