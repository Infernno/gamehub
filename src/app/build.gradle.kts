import io.gamehub.buildlogic.BuildConfig
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

val keystoreProps = Properties()
val keystoreProperties = File(rootProject.rootDir, "keystore.properties")
if (keystoreProperties.exists() && keystoreProperties.isFile) {
    localProperties.inputStream().use { input ->
        keystoreProps.load(input)
    }
}

android {
    namespace = "io.gamehub"

    defaultConfig {
        applicationId = "io.gamehub.app"
        versionCode = 1
        versionName = "1.0"

        val apiKey = checkNotNull(localProps.getProperty("rawg.apiKey") ?: System.getenv()["RAWG_API_KEY"])
        buildConfigField("String", "RAWG_API_KEY", "\"$apiKey\"")
    }

    signingConfigs {
        create("release") {
            keyAlias = keystoreProps.getProperty("alias")
            keyPassword = keystoreProps.getProperty("password")
            storeFile = File(rootProject.rootDir, "keystore.jks")
            storePassword = keystoreProps.getProperty("password")
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx.core)
    implementation(libs.androidx.metrics)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.google.material)
    implementation(libs.okhttp)

    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(projects.feature.home)
    implementation(projects.feature.search)
    implementation(projects.feature.gameDetails)
    implementation(projects.feature.releaseCalendar)
    implementation(projects.feature.filter)
}
