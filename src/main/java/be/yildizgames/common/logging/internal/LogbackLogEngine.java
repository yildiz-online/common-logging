/*
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 *  Copyright (c) 2019 Grégory Van den Borre
 *
 *  More infos available: https://engine.yildiz-games.be
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

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.logging.LogEngine;
import be.yildizgames.common.logging.LoggerConfiguration;
import be.yildizgames.common.logging.PatternBuilder;
import be.yildizgames.common.logging.PreLogger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Logback implementation for the LogEngine.
 * @author Grégory Van den Borre
 */
public class LogbackLogEngine implements LogEngine {

    /**
     * Prelogger to use before logger is initialized.
     */
    private final PreLogger preLogger = new PreLogger();

    /**
     * Generator to build a configuration file.
     */
    private final LogbackConfigFileGenerator generator = new LogbackConfigFileGenerator();

    /**
     * Create a new instance.
     */
    public LogbackLogEngine() {
        this.configureForJBoss();
    }

    @Override
    public final PatternBuilder createPatternBuilder() {
        return new LogbackPatternBuilder();
    }

    @Override
    public final void setConfigurationPath(final String path) {
        ImplementationException.throwForNull(path);
        System.setProperty("logback.configurationFile", path);
        System.setProperty("logging.config", path);
    }

    @Override
    public final void configureFromProperties(final LoggerConfiguration properties) throws IOException {
        ImplementationException.throwForNull(properties);
        String result = this.generator.generate(properties);
        Path path = Paths.get(properties.getLoggerConfigurationFile());
        Files.createDirectories(path.getParent());
        Files.write(path, result.getBytes(StandardCharsets.UTF_8));
        this.setConfigurationPath(properties.getLoggerConfigurationFile());
    }

    @Override
    public final PreLogger getPrelogger() {
        return this.preLogger;
    }

    /**
     * Force the systems from jboss to use slf4j and logback.
     */
    private void configureForJBoss() {
        System.setProperty("org.jboss.logging.provider", "slf4j");
    }


}
