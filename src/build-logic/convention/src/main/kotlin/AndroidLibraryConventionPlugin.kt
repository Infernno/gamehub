import com.android.build.gradle.LibraryExtension
import io.gamehub.buildlogic.BuildConfig
import io.gamehub.buildlogic.configureCommonDeps
import io.gamehub.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = BuildConfig.COMPILE_SDK

                defaultConfig.apply {
                    minSdk = BuildConfig.MIN_SDK
                    targetSdk = BuildConfig.TARGET_SDK

                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                    consumerProguardFiles("consumer-rules.pro")
                }

                buildTypes {
                    getByName("debug") {
                        isMinifyEnabled = false
                    }
                    getByName("release") {
                        isMinifyEnabled = BuildConfig.MINIFY
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro"
                        )
                    }
                }

                configureKotlin(this)
                configureCommonDeps()
            }
        }
    }
}
