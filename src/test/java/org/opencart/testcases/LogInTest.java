package org.opencart.testcases;

import org.opencart.pageobjects.HomePage;
import org.opencart.pageobjects.LogInPage;
import org.opencart.pageobjects.MyAccountPage;
import org.opencart.testbase.OpenCartTestBase;
import org.opencart.dataproviders.DataProvidersUtility;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.opencart.constants.TestConstant.EXPECTED_MY_ACCOUNT_PAGE_TITLE;

public class LogInTest extends OpenCartTestBase {

    @Test
    public void verifyLoginWithValidCredential() {
        log.info("Login test case: ");

        HomePage homePage = new HomePage(driver);
        homePage.clickOnMyAccount();
        homePage.clickLogin();

        log.info("logging into account: ");
        try {
            LogInPage logInPage = new LogInPage(driver);
            logInPage.setEmail(profile.getLoginEmailId());
            logInPage.setPassword(profile.getPassword());
            logInPage.clickLogin();
        } catch (Exception e) {
            log.error("failed to login: ");
            Assert.fail("An exception occurred: " + e.getMessage());
        }

        MyAccountPage myAccountPage = new MyAccountPage(driver);
        boolean targetPageHeader = myAccountPage.isMyAccountHeaderPresent();
        String pageTitle = myAccountPage.getPageTitle();

        Assert.assertTrue(targetPageHeader, "Login Failed");
        Assert.assertEquals(pageTitle, EXPECTED_MY_ACCOUNT_PAGE_TITLE);

        log.info("Logged in successfully: ");

        myAccountPage.clickLogOut();
    }

    @Test(dataProvider = "LoginData", dataProviderClass = DataProvidersUtility.class)
    public void verifyLoginWithInvalidScenarios(String emailId, String password, String expectedMessage) {
        log.info("Login test case: ");
        try {
            HomePage homePage = new HomePage(driver);
            homePage.clickOnMyAccount();
            homePage.clickLogin();
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("logging into account: ");

        LogInPage logInPage = new LogInPage(driver);
        logInPage.setEmail(emailId);
        logInPage.setPassword(password);
        logInPage.clickLogin();

        if (expectedMessage.equalsIgnoreCase("valid")) {
            MyAccountPage myAccountPage = new MyAccountPage(driver);
            boolean targetPageHeader = myAccountPage.isMyAccountHeaderPresent();
            String pageTitle = myAccountPage.getPageTitle();
            Assert.assertTrue(targetPageHeader, "Login Failed");
            Assert.assertEquals(pageTitle, EXPECTED_MY_ACCOUNT_PAGE_TITLE);
            log.info("Logged in successfully: ");
            myAccountPage.clickLogOut();
        } else {
            String actualErrorMessage = logInPage.getErrorMessage();
            Assert.assertEquals(actualErrorMessage, expectedMessage);
            log.info("Logged in failed : " + actualErrorMessage);
        }
    }


}
