import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.gamehub.buildlogic.BuildConfig
import io.gamehub.buildlogic.configureKotlin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<BaseAppModuleExtension> {
                compileSdk = BuildConfig.COMPILE_SDK

                defaultConfig.apply {
                    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

                    minSdk = BuildConfig.MIN_SDK
                    targetSdk = BuildConfig.TARGET_SDK
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

                buildFeatures.apply {
                    viewBinding = true
                }

                configureKotlin(this)
            }
        }
    }
}
