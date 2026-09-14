pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            url = uri("https://api.mapbox.com/downloads/v2/releases/maven")
            credentials {
                // Do not change the username below.
                // It should always be "mapbox" (not your username).
                username = "mapbox"
                // Use the secret token you stored in gradle.properties as the password
                password = providers.gradleProperty("MAP_ACCESS_TOKEN").get()
            }
        }
    }
}

rootProject.name = "ST10451774IceTask3"
include(":app")
