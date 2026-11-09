plugins {
    id("net.neoforged.moddev") version "2.0.147"
    id("neoforge-mutex")
}

val modVersion = providers.gradleProperty("version").orNull ?: sc.properties.get<String>("mod.version")
val mc = sc.current.version
version = modVersion
group = property("mod.group") as String
base.archivesName = "${property("mod.id")}-neoforge-$mc"

val requiredJava = JavaVersion.VERSION_25
val logoKey = if (sc.current.parsed >= "26.2") "iconFile" else "logoFile"

sourceSets.main {
    java.exclude("com/workshopcraft/moretweaks/fabric/**", "com/workshopcraft/moretweaks/forge/**")
}

neoForge {
    version = property("deps.neo_loader") as String
    mods {
        register(property("mod.id") as String) {
            sourceSet(sourceSets.main.get())
        }
    }
    runs {
        register("client") {
            gameDirectory = rootProject.file("runs/${sc.current.project}")
            client()
        }
    }
}

java {
    withSourcesJar()
    targetCompatibility = requiredJava
    sourceCompatibility = requiredJava
    toolchain.languageVersion = JavaLanguageVersion.of(requiredJava.majorVersion)
}

tasks {
    processResources {
        val props = mapOf(
            "id" to sc.properties.get<String>("mod.id"),
            "name" to sc.properties.get<String>("mod.name"),
            "version" to modVersion,
            "author" to sc.properties.get<String>("mod.author"),
            "description" to sc.properties.get<String>("mod.description"),
            "minecraft" to sc.properties.get<String>("mod.mc_compat"),
            "neoforge" to sc.properties.get<String>("deps.neo_compat"),
            "fml" to sc.properties.get<String>("deps.neo_fml_compat"),
            "java" to requiredJava.majorVersion,
            "pack_min" to sc.properties.get<String>("mod.pack_min"),
            "pack_max" to sc.properties.get<String>("mod.pack_max"),
            "logo_key" to logoKey,
            "refmap" to "",
        )
        inputs.properties(props)
        filesMatching(listOf("META-INF/neoforge.mods.toml", "pack.mcmeta", "moretweaks.mixins.json")) { expand(props) }
        exclude("fabric.mod.json")
        exclude("moretweaks.accesswidener")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    named("createMinecraftArtifacts") {
        dependsOn("stonecutterGenerate")
    }
}
