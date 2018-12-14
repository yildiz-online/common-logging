package be.yildizgames.common.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated(since = "2.0.0", forRemoval = true)
public class LogFactory {

    private static final LogFactory INSTANCE = new LogFactory();

    @Deprecated(since = "2.0.0", forRemoval = true)
    public static LogFactory getInstance() {
        return INSTANCE;
    }

    @Deprecated(since = "2.0.0", forRemoval = true)
    public Logger getLogger(Class clazz) {
        return LoggerFactory.getLogger(clazz);
    }

    public void configureFromProperties(LoggerConfiguration config) {
    }
}
