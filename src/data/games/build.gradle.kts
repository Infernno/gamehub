plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.gamehub.data.games"
}

dependencies  {
    implementation(libs.arrow.core)
    implementation(projects.core.network)

    api(projects.data.common)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)
}
