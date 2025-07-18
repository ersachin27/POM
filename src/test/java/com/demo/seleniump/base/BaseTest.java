package com.demo.seleniump.base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.demo.seleniump.firstproject.utils.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Listeners;

@Listeners({com.demo.seleniump.firstproject.utils.MyListenerClass.class})
public class BaseTest {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();  // ThreadLocal for parallel exe
    // ✅ This is the original driver (still used internally)
   // public WebDriver driver;

    // ✅ Added to support screenshots from Listener in multithreaded runs
    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    private static ExtentReports extent;

    @BeforeSuite
    public void startExtentReport() {
        extent = ExtentManager.getInstance(); // initialize once per suite
    }

 // if  want to use cross browser take it 
    
    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser, Method method) {
    //public void setUp(Method method) {
        if (extent == null) {
            extent = ExtentManager.getInstance(); // fallback check
        }

        ExtentTest test = extent.createTest(method.getName());
        ExtentTestManager.setTest(test);

        
        
        
       //IT use only when call single browser
//        String browser = Utils.getProperty("browser");
        Log.info("Selected Browser: " + browser);
        
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
        //    driver = new ChromeDriver();
            driver.set(new ChromeDriver());
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
//            driver = new FirefoxDriver();
            driver.set(new FirefoxDriver());
            
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get().manage().window().maximize();
   //     driver.get().window().maximize();
        Log.info("Browser launched");
        ExtentTestManager.getTest().log(Status.INFO, "Browser launched: " + browser);

        driver.get().get(Utils.getProperty("url"));  
    //    driver.get(Utils.getProperty("url"));
        Log.info("Navigated to application");
        ExtentTestManager.getTest().log(Status.INFO, "Navigated to: " + Utils.getProperty("url"));

        // ✅ Store driver in ThreadLocal for listener access
     //   threadLocalDriver.set(driver);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.get().quit();
           // driver.quit();
            
            Log.info("Browser closed");
            ExtentTestManager.getTest().log(Status.INFO, "Browser closed");
        }

        // ✅ Clear ThreadLocal to prevent memory leaks
        threadLocalDriver.remove();

        ExtentTestManager.removeTest();
    }

    @AfterSuite(alwaysRun = true)
    public void flushExtentReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    // ✅ Public static getter for listener classes to access current driver
    public  WebDriver getDriver() {
        return driver.get();
    }


public void waitForTitleContains(String titlePart, int timeoutInSeconds) {
    new WebDriverWait(driver.get(), Duration.ofSeconds(timeoutInSeconds))
        .until(ExpectedConditions.titleContains(titlePart));

}
}