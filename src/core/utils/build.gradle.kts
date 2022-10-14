plugins {
    id("gamehub.android.library")
}

android {
    namespace = "io.gamehub.core.utils"
}

dependencies {
    implementation(libs.bundles.androidx.core)
    implementation(libs.google.material)

    implementation(libs.kotlinx.coroutines)
}
