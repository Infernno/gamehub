import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.gamehub.buildlogic.config.AppConfig
import io.gamehub.buildlogic.config.Plugins
import io.gamehub.buildlogic.configureCommonDependencies
import io.gamehub.buildlogic.configureKotlin
import io.gamehub.buildlogic.extensions.applyPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for setting up android app modules.
 */
@Suppress("UnstableApiUsage")
open class AndroidAppPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        applyPlugins()
        applyAppConfig()
        applyDependencies()
    }

    private fun Project.applyPlugins() {
        applyPlugin(name = Plugins.ANDROID_APPLICATION)
        applyPlugin(name = Plugins.KOTLIN_ANDROID)
    }

    private fun Project.applyAppConfig() = extensions.configure<BaseAppModuleExtension> {
        compileSdk = AppConfig.COMPILE_SDK

        defaultConfig.apply {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            minSdk = AppConfig.MIN_SDK
            targetSdk = AppConfig.TARGET_SDK
        }

        buildFeatures {
            viewBinding = true
        }

        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }

        buildTypes {
            getByName("debug") {
                isMinifyEnabled = false
            }
            getByName("release") {
                isMinifyEnabled = AppConfig.MINIFY
                proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
                )
            }
        }

        configureKotlin(this)
    }

    private fun Project.applyDependencies() {
        configureCommonDependencies()
    }
}
