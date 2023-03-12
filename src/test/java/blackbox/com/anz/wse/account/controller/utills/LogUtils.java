package blackbox.com.anz.wse.account.controller.utills;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.MDC;

public class LogUtils {
    public static ListAppender<ILoggingEvent> setLogLevel(Logger logger, ListAppender<ILoggingEvent> listAppender) {
        listAppender.start();
        logger.setLevel(Level.DEBUG);
        logger.addAppender(listAppender);
        return listAppender;
    }

    public static void removeAppender(Logger logger, ListAppender<ILoggingEvent> listAppender) {
        listAppender.stop();
        logger.detachAppender(listAppender);
        MDC.clear();
    }
}
