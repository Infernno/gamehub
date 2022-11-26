package io.gamehub.buildlogic.extensions

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal val Project.kotlin: KotlinAndroidProjectExtension
    get() = extensions.getByName("kotlin") as KotlinAndroidProjectExtension

internal fun Project.addDependencyFromVersionCatalog(callback: DependencyHandlerScope.(VersionCatalog) -> Unit) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        callback(this, libs)
    }
}

fun Project.applyPlugin(name: String) {
    pluginManager.apply(name)
}
