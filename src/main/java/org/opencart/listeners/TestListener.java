package org.opencart.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.opencart.reports.AllureUtil;
import org.opencart.reports.ExtentManager;
import org.opencart.reports.ExtentTestManager;
import org.opencart.utilities.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
        ExtentTestManager.getTest().info("Test started: "+ result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest().pass("Test Passed");
        AllureUtil.attachText("Status", "Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {

        int currentRetry = result.getMethod().getCurrentInvocationCount();
        int maxRetry = 2;

        if (currentRetry <= maxRetry) {
            ExtentTestManager.getTest()
                    .warning("Retrying test... Attempt: " + currentRetry);

            AllureUtil.attachText("Retry", "Attempt: " + currentRetry);
        } else {
            ExtentTestManager.getTest().fail(result.getThrowable());

            byte[] screenshot =
                    ScreenshotUtil.captureScreenshotBytes(result.getName());

            String path =
                    ScreenshotUtil.captureScreenshotFile(result.getName());

            if (path != null) {
                ExtentTestManager.getTest().addScreenCaptureFromPath(path);
            }

            AllureUtil.attachScreenshot("Final Failure", screenshot);
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentTestManager.getTest().info("Test finished: ");
        extent.flush();
    }
}