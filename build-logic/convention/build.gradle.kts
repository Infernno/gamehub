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
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "gamehub.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidComposeLibrary") {
            id = "gamehub.android.composeLibrary"
            implementationClass = "AndroidComposeLibraryConventionPlugin"
        }
        register("androidComposeFeature") {
            id = "gamehub.android.composeFeature"
            implementationClass = "AndroidComposeFeatureConventionPlugin"
        }
        register("androidComposeApp") {
            id = "gamehub.android.composeApp"
            implementationClass = "AndroidComposeApplicationConventionPlugin"
        }
        register("kotlinJvmLibrary") {
            id = "gamehub.kotlin.jvm"
            implementationClass = "KotlinJvmLibraryPlugin"
        }
    }
}
