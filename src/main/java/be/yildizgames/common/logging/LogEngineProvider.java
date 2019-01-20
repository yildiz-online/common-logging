package be.yildizgames.common.logging;

import be.yildizgames.common.exception.implementation.ImplementationException;

import java.util.ServiceLoader;

/**
 * Provide the log engine.
 * @author Grégory Van den Borre
 */
public interface LogEngineProvider {

    /**
     * Provide the engine.
     * @return The log engine.
     */
    LogEngine getLogEngine();

    static LogEngineProvider getLoggerProvider() {
        ServiceLoader<LogEngineProvider> provider = ServiceLoader.load(LogEngineProvider.class);
        return provider.findFirst().orElseThrow(() -> ImplementationException.missingImplementation("logger"));
    }
}
