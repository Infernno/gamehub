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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "GameHub"
include(":app")
include(":core:ui")
include(":core:common")
include(":core:network")
include(":data:games")
include(":feature:home")
include(":feature:search")
include(":feature:release-calendar")
include(":feature:game-details")
include(":feature:filter")
include(":core:database")
