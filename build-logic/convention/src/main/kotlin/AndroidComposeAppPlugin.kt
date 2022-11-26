import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import io.gamehub.buildlogic.configureCompose
import io.gamehub.buildlogic.configureDaggerHilt
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidComposeAppPlugin : AndroidAppPlugin() {
    override fun apply(project: Project) {
        super.apply(project)

        project.run {
            extensions.configure<BaseAppModuleExtension> {
                configureCompose(this)
            }

            configureDaggerHilt()
        }
    }
}
