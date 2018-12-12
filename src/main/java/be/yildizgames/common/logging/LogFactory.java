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

package be.yildizgames.common.logging;

import be.yildizgames.common.exception.implementation.ImplementationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Create logger instances.
 * By default it will try to use the logback.xml configuration file, but the configuration can be overridden by code.
 * @author Grégory Van den Borre
 */
public class LogFactory {

    private static final LogFactory INSTANCE = new LogFactory();

    private boolean manual = false;

    private LoggerImplementation implementation = new LogbackLoggerImplementation();

    LogFactory() {
        super();
    }

    public static LogFactory getInstance() {
        return INSTANCE;
    }

    public Logger getLogger(Class clazz) {
        if (manual) {
            return this.implementation.getLogger(clazz);
        }
        return LoggerFactory.getLogger(clazz);
    }

    /**
     * Create a new pattern builder.
     * @return The created pattern builder.
     */
    public final PatternBuilder createPatternBuilder() {
        return new LogbackPatternBuilder();
    }

    public final void configureFromProperties(LoggerConfiguration properties) {
        switch (properties.getOutput()) {
            case TCP:
                this.configureForTcp(properties.getTcpHost(), properties.getTcpPort(), properties.getLevel(), properties.getPattern());
                break;
            default:
                this.configureForFile(properties.getFile(), properties.getLevel(), properties.getPattern());
        }
    }

    /**
     * Configure manually the logger to write to a file.
     * @param file File where logs are written.
     * @param level Level to use.
     */
    public final void configureForFile(String file, LoggerLevel level) {
        this.configureForFile(file, level, "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{20} - %msg%n");
    }

    /**
     * Configure manually the logger to write to a file.
     * @param file File where logs are written.
     * @param level Level to use.
     * @param pattern logging pattern.
     */
    public final void configureForFile(String file, LoggerLevel level, String pattern) {
        if(manual) {
            return;
        }
        ImplementationException.throwForNull(file);
        this.manual = true;
        this.implementation.configureForFile(file, level, pattern);
    }

    /**
     * Configure manually the logger to write to a tcp input.
     * @param host TCP host.
     * @param port TCP port.
     * @param level Level to use.
     * @param pattern logging pattern.
     */
    public final void configureForTcp(String host, int port, LoggerLevel level, String pattern) {
        if(manual) {
            return;
        }
        ImplementationException.throwForNull(host);
        this.manual = true;
        this.implementation.configureForTcp(host, port, level, pattern);
    }

}
