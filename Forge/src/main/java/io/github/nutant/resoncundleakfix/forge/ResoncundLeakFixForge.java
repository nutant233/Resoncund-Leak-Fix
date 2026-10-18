package io.github.nutant.resoncundleakfix.forge;

import io.github.nutant.resoncundleakfix.ResoncundLeakFix;
import net.minecraftforge.fml.common.Mod;

/**
 * Forge entry point. All the work happens in the common dispatcher; this mod no
 * longer touches the event bus layout at runtime.
 */
@Mod(ResoncundLeakFix.MOD_ID)
public final class ResoncundLeakFixForge {

    public ResoncundLeakFixForge() {
        ResoncundLeakFix.install();
    }
}
