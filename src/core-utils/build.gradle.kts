plugins {
    id("gamehub.android.library")
}

dependencies {
    implementation(libs.bundles.androidx.core)
    implementation(libs.google.material)

    implementation(libs.kotlinx.coroutines)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}
