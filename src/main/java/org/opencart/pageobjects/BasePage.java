package org.opencart.pageobjects;

import org.apache.logging.log4j.Logger;
import org.opencart.utilities.LoggerSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

    WebDriver driver;
    protected Logger log = LoggerSingleton.getLoggerInstance();

    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
