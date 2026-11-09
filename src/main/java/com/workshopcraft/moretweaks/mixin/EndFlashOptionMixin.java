package com.workshopcraft.moretweaks.mixin;

import net.minecraft.client.OptionInstance;
import net.minecraft.client.Options;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Options.class)
public abstract class EndFlashOptionMixin {
    private final OptionInstance<Boolean> hideEndFlash =
        OptionInstance.createBoolean("options.hideEndFlashes",
            OptionInstance.cachedConstantTooltip(Component.translatable("options.hideEndFlashes.tooltip")),
            false);

    public OptionInstance<Boolean> hideEndFlash() {
        return this.hideEndFlash;
    }

    @Inject(method = "processOptions", at = @At("TAIL"))
    private void moretweaks$persistHideEndFlash(Options.FieldAccess access, CallbackInfo ci) {
        access.process("hideEndFlashes", this.hideEndFlash);
    }
}
