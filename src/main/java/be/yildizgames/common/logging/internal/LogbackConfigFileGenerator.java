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

class LogbackConfigFileGenerator {

    private static final String INDENT = "  ";

    private static final String INDENT2 = INDENT + INDENT;

    private static final String TCP = "TCP";

    private static final String FILE = "FILE";

    private static final String CONSOLE = "CONSOLE";
    private static final String APPENDER_NAME = INDENT + "<appender name=\"";
    private static final String PATTERN = INDENT2 + "<pattern>";
    private static final String PATTERN_CLOSE = "</pattern>\n";
    private static final String APPENDER_CLOSE = INDENT + "</appender>\n";
    private static final String ENCODER_CLOSE = INDENT2 + "</encoder>\n";
    private static final String ENCODER = INDENT2 + "<encoder>\n";

    final String generate(LoggerConfiguration configuration) {
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
