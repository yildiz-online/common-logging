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
import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.FileAppender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

/**
 * Create logger instances.
 * By default it will try to use the logback.xml configuration file, but the configuration can be overridden by code.
 * @author Grégory Van den Borre
 */
public class LogFactory {

    private static final LogFactory INSTANCE = new LogFactory();

    private boolean manual = false;

    private Appender<ILoggingEvent> appender;

    private Level level = Level.INFO;

    LogFactory() {
        super();
    }

    public static LogFactory getInstance() {
        return INSTANCE;
    }

    public Logger getLogger(Class clazz) {
        if (manual) {
            ch.qos.logback.classic.Logger l = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(clazz);
            l.addAppender(this.appender);
            l.setLevel(level);
            return l;
        }
        return LoggerFactory.getLogger(clazz);
    }

    public void configureForFile(String file, Level level) {
        ImplementationException.throwForNull(file);
        ImplementationException.throwForNull(level);
        if(manual) {
            return;
        }
        this.manual = true;
        this.level = level;
        LoggerContext context = (LoggerContext) org.slf4j.LoggerFactory.getILoggerFactory();
        PatternLayoutEncoder ple = new PatternLayoutEncoder();
        ple.setContext(context);
        ple.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        ple.setCharset(StandardCharsets.UTF_8);
        ple.start();
        FileAppender<ILoggingEvent> fileAppender = new FileAppender<>();
        fileAppender.setFile(file);
        fileAppender.setEncoder(ple);
        this.appender = fileAppender;
        this.appender.setContext(context);
        this.appender.start();
    }

}
