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
    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)

    api(libs.bundles.retrofi2)
    implementation(libs.arrow.core)
    implementation(libs.okhttp)

    debugImplementation(libs.chucker.debug)
    releaseImplementation(libs.chucker.noop)
}
