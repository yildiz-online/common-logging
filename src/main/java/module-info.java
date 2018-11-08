/**
 * @author Grégory Van den Borre
 */
module be.yildizgames.common.logging {

    requires logback.classic;
    requires slf4j.api;
    requires logback.core;
    requires be.yildizgames.common.exception;

    exports be.yildizgames.common.logging;
}