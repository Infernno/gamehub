plugins {
    id("gamehub.android.library")
    id("kotlin-parcelize")
}

android {
    namespace = "io.gamehub.core.navigation"

}

dependencies {
    implementation(libs.androidx.navigation.compose)
}
