package org.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[text() = 'My Account']")
    private WebElement myAccountElement;

    @FindBy(xpath = "//a[normalize-space() = 'Register']" )
    private WebElement registerElement;

    public void clickOnMyAccount(){
        log.info("Click MyAccount: " + myAccountElement);
        myAccountElement.click();
    }

    public void clickOnRegisterLink(){
        log.info("Click Register link: " + registerElement);
        registerElement.click();
    }
}
