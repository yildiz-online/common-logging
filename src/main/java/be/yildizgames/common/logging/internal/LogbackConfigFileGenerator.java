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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogbackConfigFileGenerator {

    public final void generate(LoggerConfiguration configuration) throws IOException {
        LogbackLoggerLevelMapper mapper = new LogbackLoggerLevelMapper();
        StringBuilder builder = new StringBuilder();
        builder.append("<configuration>\n");
        String appender;
        switch (configuration.getOutput()) {
            case TCP:
                this.generateTcp(builder,configuration);
                appender = "TCP";
                break;
            case FILE:
                this.generateFile(builder, configuration);
                appender = "FILE";
            break;
            default:
                this.generateConsole(builder, configuration);
                appender = "STDOUT";
            break;
        }
        builder
                .append("<root level=\"")
                .append(mapper.map(configuration.getLevel()).toString())
                .append("\">\n")
                .append("<appender-ref ref=\"")
                .append(appender)
                .append("\" />\n")
                .append("</root>\n")
                .append("</configuration>\n");
        this.writeToFile(configuration, builder.toString());
    }

    private void generateConsole(StringBuilder builder, LoggerConfiguration configuration) {
        builder
                .append("<appender name=\"STDOUT\" class=\"ch.qos.logback.core.ConsoleAppender\">\n")
                .append("<encoder>\n")
                .append("<pattern>\n")
                .append(configuration.getPattern())
                .append("</pattern>\n")
                .append("</encoder>\n")
                .append("</appender>\n");
    }

    private void generateFile(StringBuilder builder, LoggerConfiguration configuration) {
        builder
                .append("<timestamp key=\"byDay\" datePattern=\"yyyy-MM-dd\"/>\n\n")
                .append("<appender name=\"FILE\" class=\"ch.qos.logback.core.FileAppender\">\n")
                .append("<file>")
                .append(configuration.getOutputFile())
                .append("-${byDay}.txt </file>\n")
                .append("<append>true</append>\n")
                .append("<encoder>\n")
                .append("<pattern>\n")
                .append(configuration.getPattern())
                .append("</pattern>\n")
                .append("</encoder>\n")
                .append("</appender>\n");
    }

    private void generateTcp(StringBuilder builder, LoggerConfiguration configuration) {
        builder
                .append("<appender name=\"TCP\" class=\"com.splunk.logging.TcpAppender\">")
                .append("<RemoteHost>")
                .append(configuration.getTcpHost())
                .append("</RemoteHost>")
                .append("<Port>")
                .append(configuration.getTcpPort())
                .append("</Port>")
                .append("<layout class=\"ch.qos.logback.classic.PatternLayout\">")
                .append("<pattern>")
                .append(configuration.getPattern())
                .append("</pattern>")
                .append("</layout>")
                .append("</appender>");
    }

    private void writeToFile(LoggerConfiguration configuration, String generated) throws IOException {
        Path path = Paths.get(configuration.getConfigurationFile());
        Files.write(path, generated.getBytes(StandardCharsets.UTF_8));
    }
}
