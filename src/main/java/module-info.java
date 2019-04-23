import be.yildizgames.common.logging.SystemLoggerSlf4jProvider;

/**
 * @author Grégory Van den Borre
 */
module be.yildizgames.common.logging {

    requires be.yildizgames.common.exception;
    requires be.yildizgames.common.util;
    requires slf4j.api;

    provides java.lang.System.LoggerFinder with SystemLoggerSlf4jProvider;

    exports be.yildizgames.common.logging;
}