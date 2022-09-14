import java.util.Properties

plugins {
    id("gamehub.android.application")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

val localProps = Properties()
val localProperties = File(rootProject.rootDir, "local.properties")
if (localProperties.exists() && localProperties.isFile) {
    localProperties.inputStream().use { input ->
        localProps.load(input)
    }
}

android {
    defaultConfig {
        applicationId = "io.gamehub.app"
        versionCode = 1
        versionName = "1.0"

        val apiKey = checkNotNull(localProps.getProperty("rawg.apiKey"))
        buildConfigField("String", "RAWG_API_KEY", "\"$apiKey\"")
    }
}

dependencies {
    implementation(libs.bundles.androidx.core)
    implementation(libs.bundles.androidx.navigation)
    implementation(libs.google.material)

    implementation(libs.okhttp)

    implementation(libs.dagger2.hilt.runtime)
    kapt(libs.dagger2.hilt.compiler)

    implementation(projects.featureHome)
    implementation(projects.featureSearch)

    implementation(projects.coreNetwork)
    implementation(projects.coreNavigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}
