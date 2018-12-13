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

import be.yildizgames.common.logging.PatternBuilder;

/**
 * Logback implementation for the pattern builder.
 * @author Grégory Van den Borre
 */
class LogbackPatternBuilder implements PatternBuilder {

    /**
     * To be replaced value by the separator during the builder build invocation.
     */
    private static final String TEMP_SEPARATOR = "#-%-#";

    /**
     * Builder to create the pattern.
     */
    private final StringBuilder builder = new StringBuilder();

    /**
     * Separator between all the data in the logged line.
     */
    private String separator = " ";

    /**
     * Create a new instance.
     */
    LogbackPatternBuilder() {
        super();
    }

    @Override
    public final PatternBuilder withSeparator(String separator) {
        this.separator = separator;
        return this;
    }

    @Override
    public final PatternBuilder context(String context) {
        this.builder.append(context);
        this.builder.append(TEMP_SEPARATOR);
        return this;
    }

    @Override
    public final PatternBuilder logger() {
        this.builder.append("%logger" + TEMP_SEPARATOR);
        return this;
    }

    @Override
    public final PatternBuilder clazz() {
        this.builder.append("%class" + TEMP_SEPARATOR);
        return this;
    }

    @Override
    public final PatternBuilder level() {
        this.builder.append("%level" + TEMP_SEPARATOR);
        return this;
    }

    @Override
    public final PatternBuilder message() {
        this.builder.append("%msg" + TEMP_SEPARATOR);
        return this;
    }

    @Override
    public final PatternBuilder date() {
        this.builder.append("%d{HH:mm:ss.SSS}" + TEMP_SEPARATOR);
        return this;
    }

    @Override
    public final PatternBuilder thread() {
        this.builder.append("[%thread]" + TEMP_SEPARATOR);
        return this;
    }

    @Override
    public final String build() {
        return this.builder.substring(0, this.builder.length() - TEMP_SEPARATOR.length()).replaceAll(TEMP_SEPARATOR, this.separator) + "%n";
    }
}
