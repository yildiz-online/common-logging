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
