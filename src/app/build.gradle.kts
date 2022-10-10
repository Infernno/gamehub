import java.util.*

plugins {
    id("gamehub.android.composeApp")
}

val localProps = Properties()
val localProperties = File(rootProject.rootDir, "local.properties")
if (localProperties.exists() && localProperties.isFile) {
    localProperties.inputStream().use { input ->
        localProps.load(input)
    }
}

android {
    namespace = "io.gamehub"

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
    implementation(libs.androidx.metrics)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.google.material)
    implementation(libs.okhttp)

    implementation(projects.core.ui)
    implementation(projects.core.network)
    implementation(projects.core.navigation)

    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.gameDetails)
    implementation(projects.feature.releaseCalendar)
}
