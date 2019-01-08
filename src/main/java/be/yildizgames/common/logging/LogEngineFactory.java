package be.yildizgames.common.logging;

import be.yildizgames.common.logging.internal.LogbackLogEngine;

/**
 * Provide the log engine.
 * @author Grégory Van den Borre
 */
public class LogEngineFactory {

    /**
     * Engine unique instance.
     */
    private static final LogbackLogEngine ENGINE = new LogbackLogEngine();

    /**
     * Provide the engine.
     * @return The log engine.
     */
    public static LogEngine getLogEngine() {
        return ENGINE;
    }
}
