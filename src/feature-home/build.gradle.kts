plugins {
    id("gamehub.android.library")
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.google.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}