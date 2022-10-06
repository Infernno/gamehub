@file:Suppress("UnstableApiUsage")

package io.gamehub.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

/**
 * Configure Kotlin
 */
internal fun Project.configureKotlin(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {

        compileOptions {
            sourceCompatibility = BuildConfig.JVM_TARGET
            targetCompatibility = BuildConfig.JVM_TARGET
        }

        kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-opt-in=kotlin.RequiresOptIn",
                // Enable experimental APIs
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )

            // Set JVM target to 1.8
            jvmTarget = BuildConfig.JVM_TARGET.toString()
        }
    }
}

internal fun Project.configureCommonDeps() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("testImplementation", libs.findLibrary("junit").get())
        add("androidTestImplementation", libs.findBundle("androidx-test").get())
    }
}

internal fun Project.configureDaggerHilt() {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("implementation", libs.findLibrary("dagger2-hilt-runtime").get())
        add("kapt", libs.findLibrary("dagger2-hilt-compiler").get())
    }
}

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures.apply {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion =
                libs.findVersion("androidxComposeCompiler").get().toString()
        }
    }

    dependencies {
        add("implementation", libs.findBundle("androidx-compose-runtime").get())
        add("debugImplementation", libs.findBundle("androidx-compose-tooling").get())
        add("androidTestImplementation", libs.findBundle("androidx-compose-test").get())
    }
}
