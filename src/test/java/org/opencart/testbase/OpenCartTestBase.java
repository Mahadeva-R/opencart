package org.opencart.testbase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.opencart.utilities.DriverFactory;
import org.opencart.utilities.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import static org.opencart.testbase.Profile.getProfileInstance;

public class OpenCartTestBase {

    protected WebDriver driver;
    protected Logger log = LoggerSingleton.getLoggerInstance();
    private Profile profile;

    @BeforeClass
    @Parameters({"os", "browser"})
    public void setUpBrowser(@Optional String os, @Optional String browser) {
        profile = getProfileInstance();

        // Read from properties if not provided
        if (browser == null || browser.isEmpty()) {
            browser = profile.getBrowser();
        }

        if (os == null || os.isEmpty()) {
            os = profile.getOs();
        }

        DriverFactory.initDriver(os, browser);
        driver = DriverFactory.getDriver();

        driver.get(profile.getAppUrl());
        log.info("Navigated to url: " + driver.getCurrentUrl());
    }

    @AfterClass
    public void tearDown() {
        log.info("Closing the browser");
        DriverFactory.quitDriver();
    }

    public String randomString() {
        return RandomStringUtils.randomAlphabetic(5);
    }

    public String randomAlphaNumeric() {
        return RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(3);
    }

    public String randomNumber() {
        return RandomStringUtils.randomNumeric(10);
    }
}
