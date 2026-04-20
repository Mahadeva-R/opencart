package org.opencart.pageobjects;

import org.opencart.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogInPage extends BasePage{

    public LogInPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "input-email")
    private WebElement emailTextField;

    @FindBy(xpath = "//input[@placeholder='Password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//div[@class='form-group']//a[text()='Forgotten Password']")
    private WebElement forgottenPasswordLink;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@id='account-login']//div[contains(@class,'alert-danger')]")
    private WebElement errorMessageHeader;

    public void setEmail(String email){
        log.info("username(email): " + email);
        emailTextField.sendKeys(email);
    }

    public void setPassword(String password){
        log.info("Password: " + password);
        passwordTextField.sendKeys(password);
    }

    public void clickOnForgottenPassword(){
        log.info("Forgotten password " + forgottenPasswordLink );
        forgottenPasswordLink.click();
    }

    public void clickLogin(){
        log.info("click login button "+ loginButton);
        loginButton.click();
    }

    public String getErrorMessage(){
        try {
            new WaitUtils(driver).waitForPageLoad();
            log.info("Fetching response message: ");
            return errorMessageHeader.getText();
        } catch (Exception e){
            log.error("Failed to fetch text message: ");
            return e.getMessage();
        }
    }

}
