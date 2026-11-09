pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.neoforged.net/releases/") { name = "NeoForged" }
        maven("https://maven.kikugie.dev/releases") { name = "KikuGie Releases" }
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.9.8"
}

stonecutter {
    create(rootProject) {
        version("26.1.2-neoforge", "26.1.2").buildscript("build.neoforge.gradle.kts")
        vcsVersion = "26.1.2-neoforge"
    }
}

rootProject.name = "moretweaks"
