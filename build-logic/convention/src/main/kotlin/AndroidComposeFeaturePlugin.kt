import com.android.build.gradle.LibraryExtension
import io.gamehub.buildlogic.addDependencyFromVersionCatalog
import io.gamehub.buildlogic.configureDaggerHilt
import io.gamehub.buildlogic.extensions.implementation
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

@Suppress("UnstableApiUsage")
class AndroidComposeFeaturePlugin : AndroidComposeLibraryPlugin() {
    override fun apply(project: Project) {
        super.apply(project)

        project.run {
            applyConfiguration()
            applyDependencies()
        }
    }

    private fun Project.applyConfiguration() = extensions.configure<LibraryExtension> {
        testOptions {
            unitTests {
                isIncludeAndroidResources = true
            }
        }
    }

    private fun Project.applyDependencies() {
        configureDaggerHilt()

        addDependencyFromVersionCatalog { libs ->
            implementation(libs.findBundle("orbit-mvi").get())
        }
    }
}
