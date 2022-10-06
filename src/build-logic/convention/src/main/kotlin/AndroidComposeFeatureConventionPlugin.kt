import com.android.build.gradle.LibraryExtension
import io.gamehub.buildlogic.configureDaggerHilt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidComposeFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("gamehub.android.composeLibrary")
                apply("org.jetbrains.kotlin.kapt")
                apply("dagger.hilt.android.plugin")
            }

            extensions.configure<LibraryExtension> {
                configureDaggerHilt()
            }
        }
    }
}
