import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.gamehub.buildlogic.configureCompose
import io.gamehub.buildlogic.configureDaggerHilt
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidComposeApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("gamehub.android.application")
                apply("org.jetbrains.kotlin.kapt")
                apply("dagger.hilt.android.plugin")
            }

            extensions.configure<BaseAppModuleExtension> {
                configureDaggerHilt()
                configureCompose(this)
            }
        }
    }
}
