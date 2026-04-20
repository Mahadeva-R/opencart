package org.opencart.utilities;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected static Logger log = LoggerSingleton.getLoggerInstance();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void initDriver(String os, String browser) {

        log.info("Running on OS: " + os);
        log.info("Launching browser: " + browser);

        WebDriver webDriver;

        switch (browser.toLowerCase()) {

            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("ignore-certificate-errors");
                webDriver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                webDriver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                webDriver = new EdgeDriver();
                break;

            default:
                throw new IllegalArgumentException("Browser not supported: " + browser);
        }

        // Common setup
        webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.set(webDriver);
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}