package com.workshopcraft.moretweaks;

import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;

/**
 * Runtime accessor for the "Hide End Flashes" option injected into {@link net.minecraft.client.Options}
 * by {@code EndFlashOptionMixin}.
 *
 * <p>The option is added by a mixin, which is not on the mod's compile classpath, so we resolve it
 * reflectively at runtime.
 */
public final class ClientOptions {
    private ClientOptions() {}

    /** @return the injected option, or {@code null} if it could not be resolved. */
    public static OptionInstance<Boolean> optionInstance() {
        Object options = Minecraft.getInstance().options;
        try {
            var m = options.getClass().getMethod("hideEndFlash");
            return (OptionInstance<Boolean>) m.invoke(options);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Whether the player has enabled "Hide End Flashes".
     *
     * @return {@code false} if the option could not be resolved (mixin not yet applied), which
     *         preserves vanilla behaviour.
     */
    public static boolean hideEndFlash() {
        OptionInstance<Boolean> option = optionInstance();
        return option != null && option.get();
    }
}
