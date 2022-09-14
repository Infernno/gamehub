plugins {
    id("gamehub.android.library")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

dependencies {
    implementation(libs.bundles.androidx.core)
    implementation(libs.bundles.androidx.navigation)

    implementation(libs.arrow.core)
    implementation(libs.kotlinx.coroutines)

    implementation(libs.adapterDelegates)
    implementation(libs.viewBindingDelegate)
    implementation(libs.glide)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)

    implementation(projects.dataGames)
    implementation(projects.coreUi)
    implementation(projects.coreUtils)
    implementation(projects.coreNavigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}
