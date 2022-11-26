@file:Suppress("UnstableApiUsage")

package io.gamehub.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import io.gamehub.buildlogic.config.AppConfig
import io.gamehub.buildlogic.config.Plugins
import io.gamehub.buildlogic.extensions.*
import io.gamehub.buildlogic.extensions.addDependencyFromVersionCatalog
import io.gamehub.buildlogic.extensions.kotlinOptions
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

/**
 * Common kotlin setup for project.
 */
internal fun Project.configureKotlin(
    commonExtension: CommonExtension<*, *, *, *>,
) = commonExtension.apply {

    compileOptions {
        sourceCompatibility = AppConfig.JVM_TARGET
        targetCompatibility = AppConfig.JVM_TARGET
    }

    kotlinOptions {
        // Treat all Kotlin warnings as errors (disabled by default)
        allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

        freeCompilerArgs = freeCompilerArgs + listOf(
            "-opt-in=kotlin.RequiresOptIn",
            // Enable experimental APIs
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )

        jvmTarget = AppConfig.JVM_TARGET.toString()
    }
}

/**
 * Setup common dependencies for Android modules.
 */
internal fun Project.configureCommonDependencies() = addDependencyFromVersionCatalog { libs ->
    testImplementation(libs.findLibrary("junit").get())
    androidTestImplementation(libs.findBundle("androidx-test").get())
}

/**
 * Adds Dagger and kotlin-kapt as dependencies.
 */
internal fun Project.configureDaggerHilt() {
    applyPlugin(name = Plugins.KOTLIN_KAPT)
    applyPlugin(name = Plugins.DAGGER_HILT)

    addDependencyFromVersionCatalog { libs ->
        implementation(libs.findLibrary("dagger2-hilt-runtime").get())
        kapt(libs.findLibrary("dagger2-hilt-compiler").get())
    }
}

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val versionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        buildFeatures.apply {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = versionCatalog.findVersion("androidxComposeCompiler")
                .get()
                .toString()
        }
    }

    addDependencyFromVersionCatalog { libs ->
        // Note: you can add any compose libraries to these bundles in libs.versions.toml
        // and they will be added to all compose modules automatically
        implementation(libs.findBundle("androidx-compose-runtime").get())
        debugImplementation(libs.findBundle("androidx-compose-tooling").get())
        androidTestImplementation(libs.findBundle("androidx-compose-test").get())
    }
}
