plugins {
    id("gamehub.android.library")
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.google.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}