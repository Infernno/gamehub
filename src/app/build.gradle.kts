import java.util.*

plugins {
    id("gamehub.android.composeApp")
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
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.google.material)
    implementation(libs.okhttp)

    implementation(projects.coreUi)
    implementation(projects.coreNetwork)
    implementation(projects.coreNavigation)

    implementation(projects.featureHome)
    implementation(projects.featureSearch)
}
