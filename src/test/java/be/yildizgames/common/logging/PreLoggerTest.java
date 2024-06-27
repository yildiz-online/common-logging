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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author Grégory Van den Borre
 */
class PreLoggerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Nested
    class Log {

        @Test
        void info() {
            PreLogger preLogger = new PreLogger();
            preLogger.info("test");
            Assertions.assertTrue(outContent.toString().trim().matches("20\\d{2}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s\\|\\sINFO\\s\\|\\sbe\\.yildizgames\\.common\\.logging\\.Prelogger\\s\\|\\stest"));
        }

        @Test
        void warn() {
            PreLogger preLogger = new PreLogger();
            preLogger.warn("test");
            Assertions.assertTrue(outContent.toString().trim().matches("20\\d{2}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s\\|\\sWARN\\s\\|\\sbe\\.yildizgames\\.common\\.logging\\.Prelogger\\s\\|\\stest"));
        }

        @Test
        void error() {
            PreLogger preLogger = new PreLogger();
            preLogger.error("test");
           Assertions.assertTrue(outContent.toString().trim().matches("20\\d{2}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s\\|\\sERROR\\|\\sbe\\.yildizgames\\.common\\.logging\\.Prelogger\\s\\|\\stest"));
        }
    }
}
