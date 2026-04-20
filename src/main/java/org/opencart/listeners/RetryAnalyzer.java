package org.opencart.listeners;

import org.apache.logging.log4j.Logger;
import org.opencart.utilities.LoggerSingleton;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    protected static Logger log = LoggerSingleton.getLoggerInstance();
    private int count = 0;
    private static final int maxRetry = 2;

    @Override
    public boolean retry(ITestResult result) {

        if (count < maxRetry) {
            count++;
            result.setAttribute("retryCount", count);
            log.info("Retrying test: "
                    + result.getName() + " | Attempt: " + count);
            return true;   // retry test
        }
        return false;      // stop retry
    }
}