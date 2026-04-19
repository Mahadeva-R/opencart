package org.opencart.pageobjects;

import org.opencart.utilities.WaitUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage{

    public AccountRegisterPage(WebDriver driver){
        super(driver);
    }

    @FindBy(id = "input-firstname")
    private WebElement firstNameTextField;

    @FindBy(name = "lastname")
    private WebElement lastNameTextField;

    @FindBy(id = "input-email")
    private WebElement emailTextField;

    @FindBy(xpath = "//input[@type= 'tel']")
    private WebElement telephoneTextField;

    @FindBy(xpath = "//input[@placeholder= 'Password']")
    private WebElement passwordTextField;

    @FindBy(css = "input#input-confirm" )
    private WebElement passwordConfirmTextField;

    @FindBy(xpath = "//input[@name='newsletter' and @value = '1']")
    private WebElement subscribeYesRadioButton;

    @FindBy(xpath = "//input[@name='newsletter' and @value = '0']")
    private WebElement subscribeNoRadioButton;

    @FindBy(css = "input[name='agree']")
    private WebElement privacyPolicyCheckbox;

    @FindBy(xpath = "//input[@value='Continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//div[@id='content']/h1")
    private WebElement msgConfirmation;

    public void setFirstName(String firstName){
        firstNameTextField.sendKeys(firstName);
        log.info("Entered FirstName: "+ firstNameTextField);
    }

    public void setLastName(String lastName){
        lastNameTextField.sendKeys(lastName);
        log.info("Entered LastName: "+ lastNameTextField);
    }

    public void setEmail(String email){
        emailTextField.sendKeys(email);
        log.info("Entered email: "+ emailTextField);
    }

    public void setTelephoneNumber(String telephoneNumber){
        telephoneTextField.sendKeys(telephoneNumber);
        log.info("Entered telephoneNumber: "+ telephoneTextField);
    }

    public void setPassword(String password){
        passwordTextField.sendKeys(password);
        log.info("Entered password: "+ passwordTextField);
    }

    public void setPasswordConfirm(String passwordConfirm){
        passwordConfirmTextField.sendKeys(passwordConfirm);
        log.info("Entered passwordConfirm: "+ passwordConfirmTextField);
    }

    public void selectSubscribeRadioButton(String option){
        WebElement radio;

        switch (option.toLowerCase()) {
            case "yes":
                radio = subscribeYesRadioButton;
                break;
            case "no":
                radio = subscribeNoRadioButton;
                break;
            default:
                throw new IllegalArgumentException("Invalid option: " + option);
        }

        if (!radio.isSelected()) {
            radio.click();
            log.info("Select Subscribe radio button: "+ firstNameTextField);
        }
    }

    public void selectPrivacyPolicy(){
        if(!privacyPolicyCheckbox.isSelected()){
            privacyPolicyCheckbox.click();
            log.info("Checked Privacy policy: "+ firstNameTextField);
        }
    }

    public void clickContinue(){
        new WaitUtils(driver).waitForClickable(continueButton).click();
        log.info("Click Continue button: "+ firstNameTextField);
    }

    public String getMessage(){
        try {
            new WaitUtils(driver).waitForPageLoad();
            log.info("Fetching response message: ");
            return msgConfirmation.getText();
        } catch (Exception e){
            log.error("Failed to fetch text message: ");
            return e.getMessage();
        }
    }
}
