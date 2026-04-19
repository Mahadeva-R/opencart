package org.opencart.testcases;

import org.opencart.pageobjects.AccountRegisterPage;
import org.opencart.pageobjects.HomePage;
import org.opencart.testbase.OpenCartTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AccountRegisterTest extends OpenCartTestBase {

    @Test
    public void verifyAccountRegistration(){
        log.info("Test case: Register to account as a new user! ");

        HomePage homePage = new HomePage(driver);
        log.info("Navigated to Home page: ");
        homePage.clickOnMyAccount();
        homePage.clickOnRegisterLink();

        log.info("Navigated to Account Register Page:");
        AccountRegisterPage registerPage = new AccountRegisterPage(driver);
        try {
            registerPage.setFirstName("Tim25");
            registerPage.setLastName("David");
            registerPage.setEmail("timDavid215@gmail.com");
            registerPage.setTelephoneNumber("8614628911");
            registerPage.setPassword("tim@123");
            registerPage.setPasswordConfirm("tim@123");
            registerPage.selectSubscribeRadioButton("no");
            registerPage.selectPrivacyPolicy();
            registerPage.clickContinue();
        } catch (Exception e ){
            log.error("Failed to Create an account");
            e.printStackTrace();
        }

        Assert.assertEquals(registerPage.getMessage(), "Your Account Has Been Created!");
        log.info("Created new account with username: Tim2");
    }
}
