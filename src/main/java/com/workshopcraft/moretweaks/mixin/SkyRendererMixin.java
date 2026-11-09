package com.workshopcraft.moretweaks.mixin;

import com.workshopcraft.moretweaks.ClientOptions;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.SkyRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(SkyRenderer.class)
public abstract class SkyRendererMixin {
    @Inject(method = "renderEndFlash", at = @At("HEAD"), cancellable = true)
    private void moretweaks$skipWhenHidden(PoseStack pose, float intensity, float xAngle, float yAngle, CallbackInfo ci) {
        if (ClientOptions.hideEndFlash()) {
            ci.cancel();
        }
    }
}
