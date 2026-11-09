# More Tweaks

Client-side tweaks for Minecraft. Version 1.0.0 ships one tweak.

## Hide End Flashes

**Options → Accessibility → Hide End Flashes**

When the option is on, the pink End sky glow and its light pulses are hidden. The End rumble still plays. Vanilla **Hide Sky Flashes** is unchanged and still controls lightning and other sky flashes.

The option defaults to off and is saved with Minecraft's other options.

## Builds

| Minecraft | NeoForge | Fabric |
| --- | --- | --- |
| 26.1.2 | 26.1.2.122 | Loader 0.18.6 |
| 26.2 | 26.2.0.93 | Loader 0.20.1 |
| 26.3 | 26.3.0.101 | Loader 0.19.5 |

Java 25 is required to build. Other Workshopcraft mods also publish 1.20.1 and 1.21.1, but those versions have no End flash, so this mod is not built for them.

```
./gradlew build
```

Jars are written to `versions/<version>-<loader>/build/libs/`.
