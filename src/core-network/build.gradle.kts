plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)

    api(libs.bundles.retrofi2)
    implementation(libs.arrow.core)
    implementation(libs.okhttp)
    implementation(libs.okhttp.logging)
}
