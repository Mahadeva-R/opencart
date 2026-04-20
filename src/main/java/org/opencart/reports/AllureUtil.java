package org.opencart.reports;

import io.qameta.allure.Allure;
import java.io.ByteArrayInputStream;

public class AllureUtil {

    public static void attachScreenshot(String name, byte[] screenshot) {
        if (screenshot != null) {
            Allure.addAttachment(name, new ByteArrayInputStream(screenshot));
        }
    }

    public static void attachText(String name, String message) {
        Allure.addAttachment(name, message);
    }
}
