package com.workshopcraft.moretweaks.mixin;

import com.workshopcraft.moretweaks.ClientOptions;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.options.AccessibilityOptionsScreen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.lang.reflect.Field;

@Mixin(AccessibilityOptionsScreen.class)
public abstract class AccessibilityOptionsScreenMixin {
    private static final Field LIST_FIELD = findListField();

    @Inject(method = "addOptions", at = @At("TAIL"))
    private void moretweaks$addHideEndFlashOption(CallbackInfo ci) {
        if (LIST_FIELD == null) {
            return;
        }
        OptionInstance<Boolean> option = ClientOptions.optionInstance();
        if (option == null) {
            return;
        }
        try {
            OptionsList list = (OptionsList) LIST_FIELD.get(this);
            if (list != null) {
                list.addSmall((OptionInstance<?>) option);
            }
        } catch (Exception ignored) {
        }
    }

    private static Field findListField() {
        try {
            Field f = OptionsSubScreen.class.getDeclaredField("list");
            f.setAccessible(true);
            return f;
        } catch (Exception e) {
            return null;
        }
    }
}
