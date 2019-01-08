/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2018 Grégory Van den Borre
 *
 *  More infos available: https://www.yildiz-games.be
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 *  documentation files (the "Software"), to deal in the Software without restriction, including without
 *  limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 *  of the Software, and to permit persons to whom the Software is furnished to do so,
 *  subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 *  OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 */
package be.yildizgames.common.logging.internal;

import be.yildizgames.common.logging.LoggerConfiguration;

/**
 * Generate a logback configuration file.
 * @author Grégory Van den Borre
 */
class LogbackConfigFileGenerator {

    /**
     * Simple indentation spaces.
     */
    private static final String INDENT = "  ";

    /**
     * Double indentation spaces.
     */
    private static final String INDENT2 = INDENT + INDENT;

    /**
     * TCP output.
     */
    private static final String TCP = "TCP";

    /**
     * File output.
     */
    private static final String FILE = "FILE";

    /**
     * Console output.
     */
    private static final String CONSOLE = "CONSOLE";

    /**
     * Appender tag.
     */
    private static final String APPENDER_NAME = INDENT + "<appender name=\"";

    /**
     * Pattern tag.
     */
    private static final String PATTERN = INDENT2 + "<pattern>";

    /**
     * Encoder tag.
     */
    private static final String ENCODER = INDENT2 + "<encoder>\n";

    /**
     * Closed pattern tag.
     */
    private static final String PATTERN_CLOSE = "</pattern>\n";

    /**
     * Closed appender tag.
     */
    private static final String APPENDER_CLOSE = INDENT + "</appender>\n";

    /**
     * Closed encoder tag.
     */
    private static final String ENCODER_CLOSE = INDENT2 + "</encoder>\n";

    /**
     * Generate the content from the configuration.
     * @param configuration Configuration to use.
     * @return The generated content.
     */
    final String generate(final LoggerConfiguration configuration) {
        LogbackLoggerLevelMapper mapper = new LogbackLoggerLevelMapper();
        StringBuilder builder = new StringBuilder();
        builder.append("<configuration>\n");
        String appender;
        switch (configuration.getLoggerOutput()) {
            case TCP:
                this.generateTcp(builder,configuration);
                appender = TCP;
                break;
            case FILE:
                this.generateFile(builder, configuration);
                appender = FILE;
            break;
            default:
                this.generateConsole(builder, configuration);
                appender = CONSOLE;
            break;
        }
        builder
                .append("  <root level=\"")
                .append(mapper.map(configuration.getLoggerLevel()).toString())
                .append("\">\n")
                .append("    <appender-ref ref=\"")
                .append(appender)
                .append("\" />\n")
                .append("  </root>\n")
                .append("</configuration>\n");
        return builder.toString();
    }

    /**
     * Generate content for the console output.
     * @param builder stringBuilder.
     * @param configuration configuration to use.
     */
    private void generateConsole(StringBuilder builder, LoggerConfiguration configuration) {
        builder
                .append(APPENDER_NAME)
                .append(CONSOLE)
                .append("\" class=\"ch.qos.logback.core.ConsoleAppender\">\n")
                .append(ENCODER)
                .append(PATTERN)
                .append(configuration.getLoggerPattern())
                .append(PATTERN_CLOSE)
                .append(ENCODER_CLOSE)
                .append(APPENDER_CLOSE);
    }

    /**
     * Generate content for the file output.
     * @param builder stringBuilder.
     * @param configuration configuration to use.
     */
    private void generateFile(StringBuilder builder, LoggerConfiguration configuration) {
        builder
                .append("  <timestamp key=\"byDay\" datePattern=\"yyyy-MM-dd\"/>\n")
                .append(APPENDER_NAME)
                .append(FILE)
                .append("\" class=\"ch.qos.logback.core.FileAppender\">\n")
                .append("    <file>")
                .append(configuration.getLoggerOutputFile())
                .append("-${byDay}.txt </file>\n")
                .append("    <append>true</append>\n")
                .append(ENCODER)
                .append(PATTERN)
                .append(configuration.getLoggerPattern())
                .append(PATTERN_CLOSE)
                .append(ENCODER_CLOSE)
                .append(APPENDER_CLOSE);
    }

    /**
     * Generate content for the tcp output.
     * @param builder stringBuilder.
     * @param configuration configuration to use.
     */
    private void generateTcp(StringBuilder builder, LoggerConfiguration configuration) {
        builder
                .append(APPENDER_NAME)
                .append(TCP)
                .append("\" class=\"com.splunk.logging.TcpAppender\">\n")
                .append("    <RemoteHost>")
                .append(configuration.getLoggerTcpHost())
                .append("</RemoteHost>\n")
                .append("    <Port>")
                .append(configuration.getLoggerTcpPort())
                .append("</Port>\n")
                .append("    <layout class=\"ch.qos.logback.classic.PatternLayout\">\n")
                .append(PATTERN)
                .append(configuration.getLoggerPattern())
                .append(PATTERN_CLOSE)
                .append("    </layout>\n")
                .append(APPENDER_CLOSE);
    }
}
