package org.opencart.utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static byte[] captureScreenshotBytes(String name) {
        try {
            WebDriver driver = DriverFactory.getDriver();
            return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        } catch (Exception e) {
            return null;
        }
    }

    public static String captureScreenshotFile(String name) {
        try {
            WebDriver driver = DriverFactory.getDriver();

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

            String baseDir = System.getProperty("user.dir") + File.separator + "reports";
            String screenshotDir = baseDir + File.separator + "screenshots";

            File dir = new File(screenshotDir);
            if (!dir.exists()) dir.mkdirs();

            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String fileName = name + "_" + timeStamp + ".png";
            File dest = new File(screenshotDir + File.separator + fileName);

            Files.copy(src.toPath(), dest.toPath());

            // ✅ RETURN RELATIVE PATH (important)
            return "screenshots/" + fileName;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
