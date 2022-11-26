plugins {
    `kotlin-dsl`
}

group = "io.gamehub.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "gamehub.android.application"
            implementationClass = "AndroidAppPlugin"
        }
        register("androidLibrary") {
            id = "gamehub.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidComposeLibrary") {
            id = "gamehub.android.composeLibrary"
            implementationClass = "AndroidComposeLibraryPlugin"
        }
        register("androidComposeFeature") {
            id = "gamehub.android.composeFeature"
            implementationClass = "AndroidComposeFeaturePlugin"
        }
        register("androidComposeApp") {
            id = "gamehub.android.composeApp"
            implementationClass = "AndroidComposeAppPlugin"
        }
        register("kotlinJvmLibrary") {
            id = "gamehub.kotlin.jvm"
            implementationClass = "KotlinJvmLibraryPlugin"
        }
    }
}
