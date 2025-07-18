package com.demo.seleniump.firstproject.login;

import java.sql.Driver;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.seleniump.base.BaseTest;
import com.demo.seleniump.firstproject.locators.LoginLocators;
import com.demo.seleniump.firstproject.utils.ExcelDataProvider;
import com.demo.seleniump.firstproject.utils.Log;
import com.demo.seleniump.firstproject.utils.RetryAnalyzer;
import org.testng.ITestListener;
import com.demo.seleniump.firstproject.utils.*;

//@Listeners(MyListenerClass.class)
public class verifyLoginWithExcel extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = ExcelDataProvider.class, retryAnalyzer = RetryAnalyzer.class)
    public void loginTest(String username, String password) throws InterruptedException {
        
            Log.info("Starting login test with: " + username);

            getDriver().findElement(By.id(LoginLocators.username)).sendKeys(username);
            Thread.sleep(2000);
            getDriver().findElement(By.id(LoginLocators.password)).sendKeys(password);
            Thread.sleep(3000);
            
            
        }
    }

