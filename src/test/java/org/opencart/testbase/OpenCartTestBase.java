package org.opencart.testbase;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.opencart.pageobjects.AccountRegisterPage;
import org.opencart.pageobjects.HomePage;
import org.opencart.utilities.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class OpenCartTestBase {

    protected WebDriver driver;
    protected Logger log = LoggerSingleton.getLoggerInstance();

    @BeforeClass
    public void setUpBrowser(){

        log.info("Launching CHROME browser:");
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

        driver.get("https://tutorialsninja.com/demo/index.php");
        log.info("Navigated to url: " + driver.getCurrentUrl());
    }

    @AfterClass
    public void tearDown(){
        log.info("Closing the browser");
        driver.quit();
        driver = null;
    }
}
