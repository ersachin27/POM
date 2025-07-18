package com.demo.seleniump.firstproject.login;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.seleniump.base.BaseTest;
import com.demo.seleniump.firstproject.utils.ExcelDataProvider;
import com.demo.seleniump.firstproject.utils.Log;
import com.demo.seleniump.firstproject.utils.RetryAnalyzer;

import junit.framework.Assert;

import com.demo.seleniump.firstproject.locators.LoginLocators;
import com.demo.seleniump.firstproject.AssertionConstants.LoginConstant;
import org.testng.ITestListener;
import com.demo.seleniump.firstproject.utils.*;

@Listeners(MyListenerClass.class)

public class verifyLogin extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyOpenBrowser() {
        Log.info("Application Title: " + getDriver().getTitle());

        getDriver().findElement(By.id(LoginLocators.username)).sendKeys(LoginConstant.username);
        Log.info("Entered username: " + LoginConstant.username);

        getDriver().findElement(By.id(LoginLocators.password)).sendKeys(LoginConstant.password);
        Log.info("Entered password: " + LoginConstant.password);
        
        String actualText =  getDriver().findElement(By.xpath(LoginLocators.verifyText)).getText();
        Assert.assertEquals(actualText, LoginConstant.ExceptedText);
        Log.error("Entered Text: " + LoginConstant.ExceptedText);
        
        getDriver().findElement(By.xpath(LoginLocators.logIn)).click();
       String title =  getDriver().getTitle();
       System.out.println("My Title =" +title);
        
        waitForTitleContains(LoginConstant.ExceptedDashboardTitle, 10);
        
        
        
        
    }
}
