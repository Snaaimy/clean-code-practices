package com.erfanlibrary.api.interfaces;

// handling third party libraries and code
public interface AppLogger {
    void info(String message);
    void error(String message, Throwable throwable);
}
