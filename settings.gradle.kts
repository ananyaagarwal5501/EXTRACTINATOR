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
        {

        }
        mavenCentral()
        {

        }
        maven { url= uri("https://jitpack.io")} //importing this maven jitpack.io and adding its dependency in the build.gradle
    }
}

rootProject.name = "Extractrinator"
include (":app")

