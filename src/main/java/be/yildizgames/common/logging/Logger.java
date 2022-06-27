/*
 * MIT License
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package be.yildizgames.common.logging;

/**
 * @author Grégory Van den Borre
 */
public class Logger {

    private final System.Logger logger;

    private Logger(final String name) {
        super();
        this.logger = System.getLogger(name);
    }

    public static Logger getLogger(Class clazz) {
        return new Logger(clazz.getName());
    }

    public static Logger getLogger(Object o) {
        return getLogger(o.getClass());
    }

    public final void info(String message) {
        this.logger.log(System.Logger.Level.INFO, message);
    }

    public final void info(String format, Object... params) {
        this.logger.log(System.Logger.Level.INFO, format, params);
    }

    public final void warning(String message) {
        this.logger.log(System.Logger.Level.WARNING, message);
    }

    public final void warning(String format, Object... params) {
        this.logger.log(System.Logger.Level.WARNING, format, params);
    }

    public final void error(String message) {
        this.logger.log(System.Logger.Level.ERROR, message);
    }

    public final void error(String format, Object... params) {
        this.logger.log(System.Logger.Level.ERROR, format, params);
    }

    public final void error(Throwable throwable) {
        this.logger.log(System.Logger.Level.ERROR, "", throwable);
    }

    public final void debug(String message) {
        this.logger.log(System.Logger.Level.DEBUG, message);
    }

    public final void debug(String format, Object... params) {
        this.logger.log(System.Logger.Level.DEBUG, format, params);
    }
}
