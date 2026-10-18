package io.github.nutant.resoncundleakfix;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

public final class ResoncundLeakFix {

    public static final String MOD_ID = "resoncundleakfix";

    public static final Logger LOG = LogManager.getLogger("ResoncundLeakFix");

    private static final String EVENT_WRAPPER = "io.github.lounode.eventwrapper.EventsWrapper";

    private static final String RESONCUND = "io.github.lounode.extrabotany.common.block.flower.generating.ResoncundBlockEntity";

    private static final AtomicBoolean INSTALLED = new AtomicBoolean();

    private ResoncundLeakFix() {
    }

    public static void install() {
        if (!INSTALLED.compareAndSet(false, true)) {
            return;
        }
        if (!present(EVENT_WRAPPER) || !present(RESONCUND)) {
            LOG.info("[ResoncundLeakFix] Event Wrapper or Extrabotany not installed; fix inactive");
            return;
        }
        ResoncundGlobalDispatcher.install();
    }

    private static boolean present(String className) {
        try {
            Class.forName(className, false, ResoncundLeakFix.class.getClassLoader());
            return true;
        } catch (Throwable ignored) {
            return false;
        }
    }
}
