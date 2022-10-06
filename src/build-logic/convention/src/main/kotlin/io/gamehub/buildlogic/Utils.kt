package io.gamehub.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.jetbrains.kotlin.gradle.dsl.KotlinAndroidProjectExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

internal fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

internal val Project.kotlin: KotlinAndroidProjectExtension
    get() = (this as ExtensionAware).extensions.getByName("kotlin") as KotlinAndroidProjectExtension
