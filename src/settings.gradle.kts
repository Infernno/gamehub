pluginManagement {
    includeBuild("build-logic")

    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GameHub"
include (":app")
include(":feature-home")
include(":core-utils")
include(":data-games")
include(":core-network")
include(":core-navigation")
include(":feature-search")
include(":core-ui")
