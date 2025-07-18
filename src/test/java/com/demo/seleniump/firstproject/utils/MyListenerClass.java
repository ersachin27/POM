package com.demo.seleniump.firstproject.utils;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class MyListenerClass implements ITestListener {

    public void onTestStart(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.INFO, "Test started: " + result.getName());
        } else {
            System.err.println("ExtentTest is null in onTestStart for test: " + result.getName());
        }
    }

    public void onTestSuccess(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.PASS, "Test passed: " + result.getName());

            // Capture screenshot on success
            WebDriver driver = getDriverFromResult(result);
            if (driver != null) {
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName() + "_PASS");
                test.addScreenCaptureFromPath(screenshotPath, "Success Screenshot");
            }
        } else {
            System.err.println("ExtentTest is null in onTestSuccess for test: " + result.getName());
        }
    }

    public void onTestFailure(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();

        if (test != null) {
            test.fail(result.getThrowable());

            // Capture screenshot on failure
            WebDriver driver = getDriverFromResult(result);
            if (driver != null) {
                String screenshotPath = ScreenshotUtils.captureScreenshot(driver, result.getName() + "_FAIL");
                test.addScreenCaptureFromPath(screenshotPath, "Failure Screenshot");
            }
        } else {
            System.err.println("ExtentTest is null in onTestFailure for test: " + result.getName());
        }
    }

    public void onTestSkipped(ITestResult result) {
        ExtentTest test = ExtentTestManager.getTest();
        if (test != null) {
            test.log(Status.SKIP, "Test skipped: " + result.getName());
        } else {
            System.err.println("ExtentTest is null in onTestSkipped for test: " + result.getName());
        }
    }

    public void onStart(ITestContext context) {
        // Optional
    }

    public void onFinish(ITestContext context) {
        // Optional
    }

    // Helper method to get WebDriver instance from test class
    private WebDriver getDriverFromResult(ITestResult result) {
        Object testClass = result.getInstance();
        try {
            // Assumes your BaseTest or test classes have a getDriver() method
            return (WebDriver) testClass.getClass().getMethod("getDriver").invoke(testClass);
        } catch (Exception e) {
            System.err.println("Failed to get WebDriver from test instance: " + e.getMessage());
            return null;
        }
    }
}
