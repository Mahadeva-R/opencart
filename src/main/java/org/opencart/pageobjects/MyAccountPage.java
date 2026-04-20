package org.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']/h2[contains(text(), 'My Account')]")
    private WebElement myAccountHeader;

    @FindBy(xpath = "//div[@class='list-group']//a[text()='Logout']")
    private WebElement logOutLink;

    public boolean isMyAccountHeaderPresent(){
        if (myAccountHeader.isDisplayed()){
            log.info("My account header present: ");
            return true;
        } else {
            log.error("My account header not present: ");
            return false;
        }
    }

    public void clickLogOut(){
        log.info("Logout from My Account page: ");
        logOutLink.click();
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

}
