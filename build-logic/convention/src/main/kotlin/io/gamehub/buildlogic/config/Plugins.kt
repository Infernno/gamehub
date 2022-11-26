package io.gamehub.buildlogic.config

/**
 * List of android & kotlin plugins applied in custom plugins.
 */
internal object Plugins {
    const val ANDROID_APPLICATION = "com.android.application"
    const val ANDROID_LIBRARY = "com.android.library"

    const val DAGGER_HILT = "dagger.hilt.android.plugin"

    const val KOTLIN_ANDROID = "org.jetbrains.kotlin.android"
    const val KOTLIN_JVM = "org.jetbrains.kotlin.jvm"
    const val KOTLIN_KAPT = "kotlin-kapt"
}
