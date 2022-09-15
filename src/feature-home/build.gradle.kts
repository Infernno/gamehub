plugins {
    id("gamehub.android.composeLibrary")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(libs.arrow.core)
    implementation(projects.dataGames)
}
