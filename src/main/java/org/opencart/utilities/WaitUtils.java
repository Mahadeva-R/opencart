package org.opencart.utilities;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class WaitUtils {

    private WebDriver driver;
    private WebDriverWait wait;

    private static final int DEFAULT_TIME_OUT_IN_SECONDS = 15;

    public WaitUtils(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS));
    }

    // ================= BASIC WAITS =================

    public WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }


    public WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitForPresence(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)) != null;
    }

    public boolean waitForTextToBePresent(WebElement element, String text) {
        return wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    // ================= LIST WAITS =================

    public List<WebElement> waitForAllElementsVisible(List<WebElement> elements) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    // ================= ALERT =================

    public Alert waitForAlert() {
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    // ================= FRAME =================

    public void waitForFrameAndSwitch(WebElement element) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    // ================= PAGE LOAD =================

    public void waitForPageLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
                .until(webDriver -> ((JavascriptExecutor) webDriver)
                        .executeScript("return document.readyState").equals("complete"));
    }

    // ================= CUSTOM WAIT =================

    public void fluentWait(WebElement element) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DEFAULT_TIME_OUT_IN_SECONDS))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);

        fluentWait.until(ExpectedConditions.visibilityOf(element));
    }

    // ================= HARD WAIT (AVOID BUT KEEP) =================

    public void staticWait(){
        try {
            Thread.sleep(DEFAULT_TIME_OUT_IN_SECONDS);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}