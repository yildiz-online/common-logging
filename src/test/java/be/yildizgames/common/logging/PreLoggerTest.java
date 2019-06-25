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
public class PreLoggerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Nested
    public class Log {

        @Test
        public void info() {
            PreLogger preLogger = new PreLogger();
            preLogger.info("test");
           // Assertions.assertTrue(outContent.toString().trim().matches("20\\d{2}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}| INFO |\\s:test"));
        }

        @Test
        public void warn() {
            PreLogger preLogger = new PreLogger();
            preLogger.warn("test");
           // Assertions.assertTrue(outContent.toString().trim().matches("20\\d{2}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s\\s\\s\\s\\[WARN]\\s:test"));
        }

        @Test
        public void error() {
            PreLogger preLogger = new PreLogger();
            preLogger.error("test");
         //   Assertions.assertTrue(outContent.toString().trim().matches("20\\d{2}/\\d{2}/\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\s\\s\\s\\s\\[ERROR]:test"));
        }
    }
}
