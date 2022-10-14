plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.gamehub.core.network"
}

dependencies {
    implementation(libs.okhttp)
    implementation(libs.arrow.core)
    api(libs.bundles.retrofit2)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.noop)
}
