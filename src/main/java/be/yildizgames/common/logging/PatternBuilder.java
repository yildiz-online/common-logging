/*
 This file is part of the Yildiz-Engine project, licenced under the MIT License  (MIT)
 Copyright (c) 2018-2024 Grégory Van den Borre
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
 * Create a formatted string to configure a log engine.
 *
 * @author Grégory Van den Borre
 */
public interface PatternBuilder {

    /**
     * Choose the separator to use.
     * @param separator The separator will be used between, all parts of the message, default is a space.
     * @return This object for chaining.
     */
    PatternBuilder withSeparator(String separator);

    /**
     * Specify a context, usually the application name.
     * @param context Context data.
     * @return This object for chaining.
     */
    PatternBuilder context(String context);

    /**
     * Append the logger name, usually the calling class.
     * @return This object for chaining.
     */
    PatternBuilder logger();

    /**
     * Append he calling class.
     * @return This object for chaining.
     */
    PatternBuilder clazz();

    /**
     * Append the logger level.
     * @return This object for chaining.
     */
    PatternBuilder level();

    /**
     * Append the message.
     * @return This object for chaining.
     */
    PatternBuilder message();

    /**
     * Append the date and time.
     * @return This object for chaining.
     */
    PatternBuilder date();

    /**
     * Append the current thread.
     * @return This object for chaining.
     */
    PatternBuilder thread();

    /**
     * Build the formatted string.
     * @return The generated formatted string.
     */
    String build();

}
