package dev.mrsterner.aequivaleo.utils;

import dev.mrsterner.aequivaleo.Aequivaleo;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import org.apache.logging.log4j.Logger;

public class AnalysisLogHandler
{

    private static final SimpleValueCache<Boolean> CONFIG = new SimpleValueCache<>(
      Aequivaleo.getInstance().getConfiguration().getCommon().debugAnalysisLog::get
    );

    private AnalysisLogHandler() {
        throw new IllegalStateException("Tried to initialize AnalysisLogHandler. This is a utility class.");
    }

    public static void onConfigurationReloaded(ModConfigEvent.Reloading reloadingEvent) {
        CONFIG.clear();
    }

    public static void debug(
      final Logger logger,
      final String string
    ) {
        if (CONFIG.get()) {
            logger.debug(string);
        }
    }
}
