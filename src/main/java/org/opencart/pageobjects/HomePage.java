package org.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//span[text() = 'My Account']")
    private WebElement myAccountElement;

    @FindBy(xpath = "//a[normalize-space() = 'Register']" )
    private WebElement registerElement;

    @FindBy(xpath = "//a[text()='Login']")
    private WebElement loginLink;

    public void clickOnMyAccount(){
        log.info("Click MyAccount: " + myAccountElement);
        myAccountElement.click();
    }

    public void clickOnRegisterLink(){
        log.info("Click Register link: " + registerElement);
        registerElement.click();
    }

    public void clickLogin(){
        log.info("Click on Login : "+ loginLink);
        loginLink.click();
    }
}
