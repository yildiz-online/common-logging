/**
 * @author Grégory Van den Borre
 */
module be.yildizgames.common.logging {
    requires java.base;
    requires logback.classic;
    requires slf4j.api;
    requires logback.core;

    exports be.yildizgames.common.logging;
}