package com.workshopcraft.moretweaks.mixin;

import com.workshopcraft.moretweaks.ClientOptions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.EndFlashState;
import net.minecraft.client.renderer.LightmapRenderStateExtractor;
import net.minecraft.client.renderer.state.LightmapRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightmapRenderStateExtractor.class)
public abstract class LightmapRenderStateExtractorMixin {
    @Inject(
        method = "extract",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/EndFlashState;getIntensity(F)F",
            shift = At.Shift.AFTER
        )
    )
    private void moretweaks$undoEndFlashLight(LightmapRenderState state, float partialTicks, CallbackInfo ci) {
        // Runs only when vanilla is about to add End flash intensity (Hide Sky Flashes is off).
        // Subtract the same amount first so that addition nets to zero.
        if (!ClientOptions.hideEndFlash()) {
            return;
        }
        Minecraft minecraft = Minecraft.getInstance();
        ClientLevel level = minecraft.level;
        if (level == null) {
            return;
        }
        EndFlashState endFlashState = level.endFlashState();
        if (endFlashState == null) {
            return;
        }
        float intensity = endFlashState.getIntensity(partialTicks);
        boolean bossFog;
        //? if >=26.2 {
        /*bossFog = minecraft.gui.hud.getBossOverlay().shouldCreateWorldFog();
        *///?} else {
        bossFog = minecraft.gui.getBossOverlay().shouldCreateWorldFog();
        //?}
        if (bossFog) {
            state.skyFactor -= intensity / 3.0f;
        } else {
            state.skyFactor -= intensity;
        }
    }
}
