plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "io.gamehub.core.database"
}

dependencies {
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.runtime)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)
}
