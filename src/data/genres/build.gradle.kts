plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.gamehub.data.genres"
}

dependencies  {
    implementation(projects.core.network)
    implementation(projects.core.utils)

    api(projects.data.common)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)
}
