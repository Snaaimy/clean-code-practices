package com.erfanlibrary.api.util;

import com.erfanlibrary.api.interfaces.AppLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// handling third-party logging library chapter 8
public class Log4jLogger implements AppLogger {

    private final Logger logger;

    public Log4jLogger(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}
