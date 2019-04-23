package be.yildizgames.common.logging;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

public class Sfl4jApiEngineTest {

    @Test
    public void getName() {
        SystemLoggerSlf4j engine = new SystemLoggerSlf4j(LoggerFactory.getLogger(Sfl4jApiEngineTest.class));
        Assertions.assertEquals("NOP", engine.getName());
    }
}
