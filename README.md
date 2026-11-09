# More Tweaks

Client-side tweaks for Minecraft.

Version 1.0.0 targets Minecraft 26.1.2 on NeoForge. The mod loads on the client. This version ships one tweak.

## Hide End Flashes

**Options → Accessibility → Hide End Flashes**

When the option is on, the pink End sky glow and its light pulses are hidden. The End rumble still plays. Vanilla **Hide Sky Flashes** is unchanged and still controls lightning and other sky flashes.

The option defaults to off and is saved with Minecraft's other options.

## Requirements

- Minecraft 26.1.2
- NeoForge 26.1.2
- Java 25, to build

## Install

Copy `moretweaks-neoforge-26.1.2-1.0.0.jar` into the instance `mods` folder and launch the client.

## Build

From the project root:

```
./gradlew :26.1.2-neoforge:build
```

Gradle writes the mod jar to `versions/26.1.2-neoforge/build/libs/`.
