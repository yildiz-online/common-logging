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

package be.yildizgames.common.logging;

import be.yildizgames.common.exception.implementation.ImplementationException;
import ch.qos.logback.classic.Level;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author Grégory Van den Borre
 */
class LogFactoryTest {

    //------------------------------------------------
    //Use different classes to use different loggers.-
    //------------------------------------------------

    @Nested
    class FromConfigFile {

        @Test
        void happyFlow() {

        }

        @Test
        void noConfigFile() {
            LogFactory factory = new LogFactory();
            Logger logger = factory.getLogger(Integer.class);
            Assertions.assertFalse(logger.isTraceEnabled());
            Assertions.assertTrue(logger.isDebugEnabled());
            Assertions.assertTrue(logger.isInfoEnabled());
            Assertions.assertTrue(logger.isErrorEnabled());
            Assertions.assertTrue(logger.isWarnEnabled());
        }

        @Test
        void invalidConfigFile() {

        }

    }

    @Nested
    class fromConfiguration {

        @Test
        void happyFlow() throws IOException {
            Path file = Files.createTempDirectory(String.valueOf(System.nanoTime())).resolve("test.log");
            LogFactory factory = new LogFactory();
            factory.configureForFile(file.toString(), Level.INFO);
            Logger logger = factory.getLogger(Long.class);
            Assertions.assertFalse(logger.isTraceEnabled());
            Assertions.assertFalse(logger.isDebugEnabled());
            Assertions.assertTrue(logger.isInfoEnabled());
            Assertions.assertTrue(logger.isErrorEnabled());
            Assertions.assertTrue(logger.isWarnEnabled());
        }


        @Test
        void fileNullFile() {
            LogFactory factory = new LogFactory();
            Assertions.assertThrows(ImplementationException.class, () -> factory.configureForFile(null, Level.INFO));
        }

        @Test
        void fileNullLevel() {
            LogFactory factory = new LogFactory();
            Assertions.assertThrows(ImplementationException.class, () -> factory.configureForFile("file.log", null));
        }
    }
}
