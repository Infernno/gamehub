import io.gamehub.buildlogic.config.AppConfig
import io.gamehub.buildlogic.config.Plugins
import io.gamehub.buildlogic.extensions.applyPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure

/**
 * Plugin for Kotlin/JVM modules.
 */
class KotlinJvmLibraryPlugin : Plugin<Project> {
    override fun apply(project: Project) = project.run {
        applyPlugin(name = Plugins.KOTLIN_JVM)

        extensions.configure<JavaPluginExtension> {
            sourceCompatibility = AppConfig.JVM_TARGET
            targetCompatibility = AppConfig.JVM_TARGET
        }
    }
}
