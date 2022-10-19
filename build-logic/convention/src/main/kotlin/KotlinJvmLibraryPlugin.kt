import io.gamehub.buildlogic.BuildConfig
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class KotlinJvmLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.jvm")
            }

            extensions.configure<JavaPluginExtension> {
                sourceCompatibility = BuildConfig.JVM_TARGET
                targetCompatibility = BuildConfig.JVM_TARGET
            }
        }
    }
}
