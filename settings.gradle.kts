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
                maven {

                        url = uri("https://jitpack.io")
                }
        }
}


rootProject.name = "PalletRelease"
include(":app")
include(":pallet_release_library")
include(":PalletReleaseLibrary")
