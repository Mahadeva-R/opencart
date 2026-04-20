package org.opencart.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.opencart.reports.ExtentManager;
import org.opencart.reports.ExtentTestManager;
import org.opencart.utilities.ScreenshotUtil;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    private ExtentReports extent;
    private ExtentSparkReporter sparkReporter;
    private String reportName;

    @Override
    public void onStart(ITestContext context) {
        initReport(context);
    }

    private void initReport(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        reportName = "Test-Report-" + timeStamp + ".html";

        String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + reportName;

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = ExtentManager.getInstance();
        extent.attachReporter(sparkReporter);
        setSystemInfo(context);
    }

    private void setSystemInfo(ITestContext context) {
        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Sub Module", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = context.getCurrentXmlTest().getParameter("os");
        String browser = context.getCurrentXmlTest().getParameter("browser");

        if (os != null) extent.setSystemInfo("Operating System", os);
        if (browser != null) extent.setSystemInfo("Browser", browser);

        List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        test.assignCategory(result.getMethod().getGroups());
        test.info("Test case started: " + result.getMethod().getMethodName());
        ExtentTestManager.setTest(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.PASS, result.getName() + " executed successfully");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        test.log(Status.FAIL, result.getName() + " failed");
        test.log(Status.INFO, result.getThrowable());

        String screenshotPath = ScreenshotUtil.captureScreenshotFile(result.getName());
        test.addScreenCaptureFromPath(screenshotPath);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTestManager.getTest()
                .log(Status.SKIP, result.getName() + " skipped")
                .log(Status.INFO, result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
//        openReport();
    }

    private void openReport() {
        try {
            String reportPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + reportName;
            Desktop.getDesktop().browse(new File(reportPath).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}