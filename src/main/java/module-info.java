/**
 * @author Grégory Van den Borre
 */
module be.yildizgames.common.logging {

    requires be.yildizgames.common.exception;
    requires be.yildizgames.common.util;
    requires splunk.library.javalogging;
    requires ch.qos.logback.classic;

    exports be.yildizgames.common.logging;
}