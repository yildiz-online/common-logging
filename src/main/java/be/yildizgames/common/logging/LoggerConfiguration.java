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

import java.util.List;

/**
 * Parameters to configure the logger.
 * @author Grégory Van den Borre
 */
public interface LoggerConfiguration {

    /**
     * Provide the pattern to format the message.
     * @return The message pattern.
     */
    String getLoggerPattern();

    /**
     * Provide the logger level to use.
     * @return The logger level.
     */
    LoggerLevel getLoggerLevel();

    /**
     * Provide the chosen logger output.
     * @deprecated Use getLoggerOutputs instead.
     * @return The logger output.
     */
    @Deprecated
    SupportedOutput getLoggerOutput();

    /**
     * Provide the chosen logger output.
     * @return The logger output.
     */
    default List<SupportedOutput> getLoggerOutputs() {return List.of(getLoggerOutput());}

    /**
     * Provide the host if the logger output is TCP.
     * @return The TCP output host.
     */
    String getLoggerTcpHost();

    /**
     * Provide the port if the logger output is TCP.
     * @return The TCP output port.
     */
    int getLoggerTcpPort();

    /**
     * Provide the file if the logger output is FILE.
     * @return The file output path.
     */
    String getLoggerOutputFile();

    /**
     * Provide the file where the configuration will be written to be read by the logger engine.
     * @return the configuration file path.
     */
    String getLoggerConfigurationFile();

    List<String> getLoggerToDisable();

    /**
     * Possible output for the logger.
     * @author Grégory Van den Borre
     */
    enum SupportedOutput {

        /**
         * To write the logs in a file.
         */
        FILE,

        /**
         * To send the logs over TCP.
         */
        TCP,

        /**
         * To display logs in the console.
         */
        CONSOLE

    }
}
