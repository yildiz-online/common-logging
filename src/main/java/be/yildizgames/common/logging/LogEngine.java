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

package be.yildizgames.common.logging;

import java.io.IOException;

/**
 * Create logger instances.
 * By default it will try to use the logback.xml configuration file, but the configuration can be overridden by code.
 * @author Grégory Van den Borre
 */
public interface LogEngine {

    /**
     * Create a new pattern builder.
     * @return The created pattern builder.
     */
    PatternBuilder createPatternBuilder();

    /**
     * Set the path to the logger configuration file.
     * @param path Path of the configuration file.
     */
    void setConfigurationPath(String path);

    /**
     * Build a logger configuration file from properties.
     * @param properties Logger configuration properties to build the file.
     * @throws IOException If the file cannot be written properly.
     */
    void configureFromProperties(LoggerConfiguration properties) throws IOException;
}
