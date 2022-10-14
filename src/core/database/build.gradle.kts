plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    kotlin("plugin.serialization")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.gamehub.core.database"
}

dependencies {
    implementation(libs.kotlinx.serialization)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)
}
