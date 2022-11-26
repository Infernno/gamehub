import com.android.build.gradle.LibraryExtension
import io.gamehub.buildlogic.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
open class AndroidComposeLibraryPlugin : AndroidLibraryPlugin() {
    override fun apply(project: Project) {
        super.apply(project)

        project.run {
            extensions.configure<LibraryExtension> {
                configureCompose(this)
            }
        }
    }
}
