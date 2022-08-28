plugins {
    id("gamehub.android.application")
}

android {
    defaultConfig {
        applicationId = "io.gamehub.app"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(libs.bundles.androidx)
    implementation(libs.google.material)

    testImplementation(libs.junit)
    androidTestImplementation(libs.bundles.androidx.test)
}