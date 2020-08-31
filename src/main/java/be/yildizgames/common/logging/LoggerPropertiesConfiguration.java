package be.yildizgames.common.logging;

import be.yildizgames.common.properties.PropertiesHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * Logger configuration built from a property file.
 * @author Grégory Van den Borre.
 */
public class LoggerPropertiesConfiguration implements LoggerConfiguration {

    public static final String LOGGER_PATTERN_KEY = "logger.pattern";

    public static final String LOGGER_LEVEL_KEY = "logger.level";

    public static final String LOGGER_OUTPUT_KEY = "logger.output";

    public static final String LOGGER_TCP_HOST_KEY = "logger.tcp.host";

    public static final String LOGGER_TCP_PORT_KEY = "logger.tcp.port";

    public static final String LOGGER_FILE_OUTPUT_KEY = "logger.file.output";

    public static final String LOGGER_CONFIGURATION_FILE_KEY = "logger.configuration.file";

    public static final String LOGGER_DISABLED_KEY = "logger.disabled";

    /**
     * Pattern to display to message.
     */
    private final String loggerPattern;

    /**
     * Level of the logger.
     */
    private final LoggerLevel loggerLevel;

    /**
     * Output of the logger.
     */
    private final List<SupportedOutput> loggerOutputs;

    /**
     * Host in case of TCP output.
     */
    private final String loggerTcpHost;

    /**
     * Port in case of TCP output.
     */
    private final int loggerTcpPort;

    /**
     * File in case of FILE output.
     */
    private final String loggerFile;

    /**
     * File where the configuration will be written.
     */
    private final String configurationFile;

    private final List<String> loggerToDisable;

    /**
     * Build the instance.
     * @param p Properties to build from.
     */
    private LoggerPropertiesConfiguration(final Properties p) {
        Objects.requireNonNull(p);
        this.loggerPattern = PropertiesHelper.getValue(p, LOGGER_PATTERN_KEY);
        this.loggerLevel = LoggerLevel.valueOf(PropertiesHelper.getValue(p, LOGGER_LEVEL_KEY).toUpperCase());
        this.loggerOutputs = Arrays.stream(PropertiesHelper.getValue(p, LOGGER_OUTPUT_KEY).split(","))
                .map(String::trim)
                .map(String::toUpperCase)
                .map(SupportedOutput::valueOf)
                .collect(Collectors.toList());
        this.loggerTcpHost = PropertiesHelper.getValue(p, LOGGER_TCP_HOST_KEY);
        this.loggerTcpPort = PropertiesHelper.getIntValue(p, LOGGER_TCP_PORT_KEY);
        this.loggerFile = PropertiesHelper.getValue(p, LOGGER_FILE_OUTPUT_KEY);
        this.configurationFile = PropertiesHelper.getValue(p, LOGGER_CONFIGURATION_FILE_KEY);
        String disable = PropertiesHelper.getValue(p, LOGGER_DISABLED_KEY);
        this.loggerToDisable = Arrays.asList(disable.split(","));
    }

    /**
     * Create a new configuration from a properties file.
     * Expected properties are:
     * logger.pattern: String.
     * logger.level: TRACE, DEBUG, INFO, WARN, ERROR.
     * logger.output: FILE, TCP, CONSOLE.
     * logger.tcp.host: String.
     * logger.tcp.port: a valid port number.
     * logger.file.output: String.
     * logger.configuration.file: String.
     * @param p Properties to build from.
     * @return The built configuration.
     */
    public static LoggerConfiguration fromProperties(Properties p) {
        return new LoggerPropertiesConfiguration(p);
    }

    @Override
    public final String getLoggerPattern() {
        return this.loggerPattern;
    }

    @Override
    public final LoggerLevel getLoggerLevel() {
        return this.loggerLevel;
    }

    @Override
    public final SupportedOutput getLoggerOutput() {
        return this.loggerOutputs.get(0);
    }

    @Override
    public final List<SupportedOutput> getLoggerOutputs() {
        return this.loggerOutputs;
    }

    @Override
    public final String getLoggerTcpHost() {
        return this.loggerTcpHost;
    }

    @Override
    public final int getLoggerTcpPort() {
        return this.loggerTcpPort;
    }

    @Override
    public final String getLoggerOutputFile() {
        return this.loggerFile;
    }

    @Override
    public final String getLoggerConfigurationFile() {
        return this.configurationFile;
    }

    @Override
    public final List<String> getLoggerToDisable() {
        return this.loggerToDisable;
    }

}
