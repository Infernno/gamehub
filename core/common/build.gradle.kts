plugins {
    id("gamehub.android.library")
}

android {
    namespace = "io.gamehub.core.common"
}

dependencies {
    implementation(libs.bundles.androidx.core)
    implementation(libs.orbit.mvi.viewmodel)
}
