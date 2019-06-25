package be.yildizgames.common.logging;

import java.util.Properties;

/**
 * @author Grégory Van den Borre
 */
public class LoggerPropertiesDefault extends Properties {

    public LoggerPropertiesDefault() {
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_LEVEL_KEY, "INFO");
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_OUTPUT_KEY, "CONSOLE");
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_PATTERN_KEY, "%d{yyyy/MM/dd HH:mm:ss} | %level | %class | %msg%n");
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_TCP_HOST_KEY, "localhost");
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_TCP_PORT_KEY, "60000");
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_FILE_OUTPUT_KEY, "log.log");
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_CONFIGURATION_FILE_KEY, "logback.xml");
        this.setProperty(LoggerPropertiesConfiguration.LOGGER_DISABLED_KEY, "");
    }
}
