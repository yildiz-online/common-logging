/*
 This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 Copyright (c) 2019-2024 Grégory Van den Borre
 More infos available: https://engine.yildiz-games.be
 Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright
 notice and this permission notice shall be included in all copies or substantial portions of the  Software.
 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package be.yildizgames.common.logging;

import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * This create an instance of SystemLoggerSlf4j.
 * This module provide it, so any other module requiring this module will have the system logger finder configured to
 * create instances of SystemLoggerSlf4j.
 *
 * @author Grégory Van den Borre
 */
public class SystemLoggerSlf4jProvider extends System.LoggerFinder {

    private final Map<String, System.Logger> loggers = new HashMap<>();

    @Override
    public final System.Logger getLogger(final String name, final Module module) {
        return this.loggers.computeIfAbsent(name, k -> loggers.put(name, new SystemLoggerSlf4j(LoggerFactory.getLogger(name))));
    }
    
    @Override
    public final String toString() {
        return "System Logger SLF4J finder";
    }

}
