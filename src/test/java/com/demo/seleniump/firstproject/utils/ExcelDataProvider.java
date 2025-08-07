package com.demo.seleniump.firstproject.utils;

import org.testng.annotations.DataProvider;

public class ExcelDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {
        String path = "src\\test\\resources\\testdata\\TestData.xlsx";
        return ExcelUtils.getFilteredData(path, "LoginData");
    }
}
