/*
 *
 * This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 *
 * Copyright (c) 2019 Grégory Van den Borre
 *
 * More infos available: https://engine.yildiz-games.be
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without
 * limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
 * of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial
 *  portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 *  WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT  HOLDERS BE LIABLE FOR ANY CLAIM,
 *  DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE  SOFTWARE.
 *
 *
 */

package be.yildizgames.common.logging;


import org.slf4j.Logger;

import java.text.MessageFormat;
import java.util.ResourceBundle;

/**
 * This class provide an implementation for System.Logger based on SLF4J.
 * This allow to use the system Logger facade in classes and hide SLF4J, so it is no longer necessary to import it in
 * libraries using the logger, implementation is only required to configure the logger.
 *
 * @author Grégory Van den Borre
 */
public class SystemLoggerSlf4j implements System.Logger {

    /**
     * Wrapped SLF4j logger.
     */
    private final Logger logger;

    /**
     * Build a new instance from a SLF4j logger.
     * @param logger Logger to wrap.
     */
    SystemLoggerSlf4j(final Logger logger) {
        super();
        this.logger = logger;
    }

    @Override
    public final String getName() {
        return this.logger.getName();
    }

    @Override
    public final boolean isLoggable(Level level) {
        switch(level) {
            case ALL: return true;
            case TRACE: return this.logger.isTraceEnabled();
            case DEBUG: return this.logger.isDebugEnabled();
            case INFO: return this.logger.isInfoEnabled();
            case WARNING: return this.logger.isWarnEnabled();
            case ERROR: return this.logger.isErrorEnabled();
            default: return false;
        }
    }

    @Override
    public final void log(Level level, ResourceBundle bundle, String msg, Throwable thrown) {
        switch(level) {
            case TRACE:
                this.logger.trace(msg, thrown);
                break;
            case DEBUG:
                this.logger.debug(msg, thrown);
                break;
            case INFO:
                this.logger.info(msg, thrown);
                break;
            case WARNING:
                this.logger.warn(msg, thrown);
                break;
            case ERROR:
                this.logger.error(msg, thrown);
                break;
            default:
                break;
        }
    }

    @Override
    public final void log(Level level, ResourceBundle bundle, String format, Object... params) {
        switch(level) {
            case TRACE:
                this.logger.trace(MessageFormat.format(format, params));
                break;
            case DEBUG:
                this.logger.debug(MessageFormat.format(format, params));
                break;
            case INFO:
                this.logger.info(MessageFormat.format(format, params));
                break;
            case WARNING:
                this.logger.warn(MessageFormat.format(format, params));
                break;
            case ERROR:
                this.logger.error(MessageFormat.format(format, params));
                break;
        }
    }

}
