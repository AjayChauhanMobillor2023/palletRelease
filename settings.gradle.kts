pluginManagement {
        repositories {
                google()
                mavenCentral()
                gradlePluginPortal()
        }
}
dependencyResolutionManagement {
        repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
        repositories {
                google()
                mavenCentral()
        }
}

rootProject.name = "PalletRelease"
include(":app")
include(":pallet_release_library")
include(":PalletReleaseLibrary")
