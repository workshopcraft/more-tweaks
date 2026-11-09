plugins {
    id("dev.kikugie.loom-back-compat")
}

val modVersion = providers.gradleProperty("version").orNull ?: sc.properties.get<String>("mod.version")
val modId = sc.properties.get<String>("mod.id")
val mc = sc.current.version

version = modVersion
group = sc.properties.get<String>("mod.group")
base.archivesName = "$modId-fabric-$mc"

val requiredJava = JavaVersion.VERSION_25

sourceSets.main {
    java.exclude("com/workshopcraft/moretweaks/MoreTweaks.java")
    java.exclude("com/workshopcraft/moretweaks/neoforge/**")
    java.exclude("com/workshopcraft/moretweaks/forge/**")
}

dependencies {
    minecraft("com.mojang:minecraft:${sc.current.version}")
    loomx.applyMojangMappings()
    modImplementation("net.fabricmc:fabric-loader:${sc.properties.get<String>("deps.fabric_loader")}")
}

loom {
    fabricModJsonPath = rootProject.file("src/main/resources/fabric.mod.json")
    accessWidenerPath = rootProject.file("src/main/resources/moretweaks.accesswidener")
    runConfigs.all {
        runDirectory = rootProject.file("runs/${sc.current.project}")
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
            "id" to modId,
            "name" to sc.properties.get<String>("mod.name"),
            "version" to modVersion,
            "author" to sc.properties.get<String>("mod.author"),
            "description" to sc.properties.get<String>("mod.description"),
            "minecraft" to sc.properties.get<String>("mod.mc_compat"),
            "loader" to sc.properties.get<String>("deps.fabric_loader"),
            "java" to requiredJava.majorVersion,
            "pack_min" to sc.properties.get<String>("mod.pack_min"),
            "pack_max" to sc.properties.get<String>("mod.pack_max"),
            "refmap" to "",
        )
        inputs.properties(props)
        filesMatching(listOf("fabric.mod.json", "pack.mcmeta", "moretweaks.mixins.json")) { expand(props) }
        exclude("META-INF/neoforge.mods.toml")
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }
}
