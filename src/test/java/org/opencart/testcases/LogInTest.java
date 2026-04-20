package org.opencart.testcases;

import org.opencart.pageobjects.HomePage;
import org.opencart.pageobjects.LogInPage;
import org.opencart.testbase.OpenCartTestBase;
import org.testng.annotations.Test;

public class LogInTest extends OpenCartTestBase {

    @Test
    public void verifyLogin(){
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
        } catch (Exception e){
            log.error("failed to login: ");
            e.printStackTrace();
        }

    }


}
