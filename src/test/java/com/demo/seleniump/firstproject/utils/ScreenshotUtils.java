package com.demo.seleniump.firstproject.utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destPath = System.getProperty("user.dir") + "/screenshots/" + testName + "_"+ "_" + timestamp + ".png";

            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots/")); // Create dir if not exist
            Files.copy(srcFile.toPath(), Paths.get(destPath));

            System.out.println("Screenshot saved: " + destPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return testName;
    }
}
