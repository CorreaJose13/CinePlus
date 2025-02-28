package com.correajose.cineplus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LoggingUtils {

    public static void logError(Class<?> clazz, String message, StackTraceElement[] ex) {
        Logger logger = LoggerFactory.getLogger(clazz);
        logger.error(message, ex);
    }
}
