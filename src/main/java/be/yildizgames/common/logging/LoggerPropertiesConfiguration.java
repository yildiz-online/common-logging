package be.yildizgames.common.logging;

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.util.PropertiesHelper;

import java.util.Properties;

public class LoggerPropertiesConfiguration implements LoggerConfiguration {

    private final String loggerPattern;
    private final LoggerLevel loggerLevel;
    private final SupportedOutput loggerOutput;
    private final String loggerTcpHost;
    private final int loggerTcpPort;
    private final String loggerFile;
    private final String configurationFile;

    private LoggerPropertiesConfiguration(final Properties p) {
        ImplementationException.throwForNull(p);
        this.loggerPattern = PropertiesHelper.getValue(p, "logger.pattern");
        this.loggerLevel = LoggerLevel.valueOf(PropertiesHelper.getValue(p, "logger.level").toUpperCase());
        this.loggerOutput = SupportedOutput.valueOf(PropertiesHelper.getValue(p, "logger.output").toUpperCase());
        this.loggerTcpHost = PropertiesHelper.getValue(p, "logger.tcp.host");
        this.loggerTcpPort = PropertiesHelper.getIntValue(p, "logger.tcp.port");
        this.loggerFile = PropertiesHelper.getValue(p, "logger.file.output");
        this.configurationFile = PropertiesHelper.getValue(p, "logger.configuration.file");
    }

    public static LoggerConfiguration fromProperties(Properties p) {
        return new LoggerPropertiesConfiguration(p);
    }

    @Override
    public String getLoggerPattern() {
        return this.loggerPattern;
    }

    @Override
    public LoggerLevel getLoggerLevel() {
        return this.loggerLevel;
    }

    @Override
    public SupportedOutput getLoggerOutput() {
        return this.loggerOutput;
    }

    @Override
    public String getLoggerTcpHost() {
        return this.loggerTcpHost;
    }

    @Override
    public int getLoggerTcpPort() {
        return this.loggerTcpPort;
    }

    @Override
    public String getLoggerOutputFile() {
        return this.loggerFile;
    }

    @Override
    public String getLoggerConfigurationFile() {
        return this.configurationFile;
    }
}
