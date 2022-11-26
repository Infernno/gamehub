import com.android.build.api.dsl.LibraryExtension
import io.gamehub.buildlogic.config.AppConfig
import io.gamehub.buildlogic.config.Plugins
import io.gamehub.buildlogic.configureCommonDependencies
import io.gamehub.buildlogic.configureKotlin
import io.gamehub.buildlogic.extensions.applyPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * Convention plugin for setting up android library modules.
 */
@Suppress("UnstableApiUsage")
open class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        applyPlugins()
        applyAppConfig()
        applyDependencies()
    }

    private fun Project.applyPlugins() {
        applyPlugin(name = Plugins.ANDROID_LIBRARY)
        applyPlugin(name = Plugins.KOTLIN_ANDROID)
    }

    private fun Project.applyAppConfig() = extensions.configure<LibraryExtension> {
        compileSdk = AppConfig.COMPILE_SDK

        defaultConfig.apply {
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

            minSdk = AppConfig.MIN_SDK
            targetSdk = AppConfig.TARGET_SDK
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
