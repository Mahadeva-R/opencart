package org.opencart.utilities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerSingleton {

    private static Logger log;

    private LoggerSingleton() {
    }

    public static Logger getLoggerInstance() {
        if (log == null) {
            log = LogManager.getLogger(LoggerSingleton.class.getName());
        }
        return log;
    }
}