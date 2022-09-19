plugins {
    id("gamehub.android.composeLibrary")
}

android {
    namespace = "io.gamehub.core.ui"
}

dependencies {
    implementation(libs.bundles.androidx.core)
    implementation(libs.google.material)
}
