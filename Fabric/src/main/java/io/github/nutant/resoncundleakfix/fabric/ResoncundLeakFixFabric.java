package io.github.nutant.resoncundleakfix.fabric;

import io.github.nutant.resoncundleakfix.ResoncundLeakFix;
import net.fabricmc.api.ModInitializer;

/**
 * Fabric entry point.
 */
public final class ResoncundLeakFixFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ResoncundLeakFix.install();
    }
}
