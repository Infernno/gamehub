plugins {
    id("gamehub.android.library")
}

dependencies {
    implementation(libs.bundles.androidx.core)
    api(libs.google.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}
