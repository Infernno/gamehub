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
include(":app")
include(":core:network")
include(":core:ui")
include(":core:utils")
include(":data:games")
include(":feature:home")
include(":feature:search")
include(":feature:release-calendar")
include(":feature:game-details")
include(":core:navigation")
