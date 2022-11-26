package io.gamehub.buildlogic.config

import org.gradle.api.JavaVersion

/**
 * Application config
 */
object AppConfig {
    const val MIN_SDK = 26
    const val TARGET_SDK = 32
    const val COMPILE_SDK = 33

    val JVM_TARGET = JavaVersion.VERSION_1_8

    const val MINIFY = false
}

