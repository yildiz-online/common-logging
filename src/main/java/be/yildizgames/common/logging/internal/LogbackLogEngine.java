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

import be.yildizgames.common.exception.implementation.ImplementationException;
import be.yildizgames.common.logging.LogEngine;
import be.yildizgames.common.logging.LoggerConfiguration;
import be.yildizgames.common.logging.PatternBuilder;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class LogbackLogEngine implements LogEngine {

    private final LogbackConfigFileGenerator generator = new LogbackConfigFileGenerator();

    @Override
    public PatternBuilder createPatternBuilder() {
        return new LogbackPatternBuilder();
    }

    @Override
    public void setConfigurationPath(String path) {
        ImplementationException.throwForNull(path);
        System.setProperty("logback.configurationFile", path);
    }

    @Override
    public void configureFromProperties(LoggerConfiguration properties) throws IOException {
        ImplementationException.throwForNull(properties);
        String result = this.generator.generate(properties);
        Path path = Paths.get(properties.getConfigurationFile());
        Files.createDirectories(path.getParent());
        Files.write(path, result.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        this.setConfigurationPath(properties.getConfigurationFile());
    }


}