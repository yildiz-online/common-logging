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

/**
 * This class provide an easy to use facade abode the System.Logger class.
 *
 * @author Grégory Van den Borre
 */
public class Logger {

    private final System.Logger log;

    private Logger(final String name) {
        super();
        this.log = System.getLogger(name);
    }

    /**
     * Create a new logger instance for the given class.
     *
     * @param clazz Class to get the logger for.
     * @return The logger.
     */
    public static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz.getName());
    }

    /**
     * Create a new logger instance for the given object.
     *
     * @param o Object to get the logger for.
     * @return The logger.
     */
    public static Logger getLogger(Object o) {
        return getLogger(o.getClass());
    }

    /**
     * Log an info message.
     *
     * @param message Message to log.
     */
    public final void info(String message) {
        this.log.log(System.Logger.Level.INFO, message);
    }

    /**
     * Log an info message.
     *
     * @param format Message to log, it is expected to follow the java.text.MessageFormat format.
     * @param params Parameters for the message format.
     */
    public final void info(String format, Object... params) {
        this.log.log(System.Logger.Level.INFO, format, params);
    }

    /**
     * Log a warning message.
     *
     * @param message Message to log.
     */
    public final void warning(String message) {
        this.log.log(System.Logger.Level.WARNING, message);
    }

    /**
     * Log a warning message.
     *
     * @param format Message to log, it is expected to follow the java.text.MessageFormat format.
     * @param params Parameters for the message format.
     */
    public final void warning(String format, Object... params) {
        this.log.log(System.Logger.Level.WARNING, format, params);
    }

    /**
     * Log an error message.
     *
     * @param message Message to log.
     */
    public final void error(String message) {
        this.log.log(System.Logger.Level.ERROR, message);
    }

    /**
     * Log an error message.
     *
     * @param format Message to log, it is expected to follow the java.text.MessageFormat format.
     * @param params Parameters for the message format.
     */
    public final void error(String format, Object... params) {
        this.log.log(System.Logger.Level.ERROR, format, params);
    }

    /**
     * Log an error message.
     *
     * @param throwable Exception to log.
     */
    public final void error(Throwable throwable) {
        this.log.log(System.Logger.Level.ERROR, "", throwable);
    }

    /**
     * Log a debug message.
     *
     * @param message Message to log.
     */
    public final void debug(String message) {
        this.log.log(System.Logger.Level.DEBUG, message);
    }

    /**
     * Log a debug message.
     *
     * @param format Message to log, it is expected to follow the java.text.MessageFormat format.
     * @param params Parameters for the message format.
     */
    public final void debug(String format, Object... params) {
        this.log.log(System.Logger.Level.DEBUG, format, params);
    }
}
