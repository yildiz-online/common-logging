package be.yildizgames.common.logging;

import be.yildizgames.common.logging.internal.LogbackLogEngine;

public class LogEngineFactory {

    public static LogEngine getLogEngine() {
        return new LogbackLogEngine();
    }
}
