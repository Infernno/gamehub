package io.gamehub.buildlogic

import org.gradle.api.JavaVersion

object BuildConfig {

    val MIN_SDK = 23
    val TARGET_SDK = 32
    val COMPILE_SDK = 33

    val JVM_TARGET = JavaVersion.VERSION_1_8

    val MINIFY = false
}
