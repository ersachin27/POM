package com.demo.seleniump.firstproject.login.homepage;

import com.demo.seleniump.HomePage.WeeklyStatusPage;
import com.demo.seleniump.base.BaseTest;
import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.demo.seleniump.firstproject.AssertionConstants.LoginConstant;
import com.demo.seleniump.firstproject.login.*;
import com.demo.seleniump.firstproject.utils.ExcelDataProvider;
import com.demo.seleniump.firstproject.utils.Log;
import com.demo.seleniump.firstproject.utils.RetryAnalyzer;

public class WeeklyStatusTest extends BaseTest {
	
	 @Test
	    public void testPrintWeeklyStatusTable() {
		 verifyLogin loginTest = new verifyLogin();
		 
	        loginTest.verifyOpenBrowser(); 
	        WeeklyStatusPage weeklyPage = new WeeklyStatusPage(getDriver());
	        weeklyPage.printWeeklyStatusTable();
	    }
	}

