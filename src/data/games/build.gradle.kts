plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.gamehub.data.games"
}

dependencies  {
    implementation(projects.core.network)
    implementation(projects.core.database)
    api(libs.arrow.core)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)
}
