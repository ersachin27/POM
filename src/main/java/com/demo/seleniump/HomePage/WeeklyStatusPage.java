package com.demo.seleniump.HomePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class WeeklyStatusPage {
	

    private WebDriver driver;

    // Constructor
    public WeeklyStatusPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to print the Weekly status table
    public void printWeeklyStatusTable() {
        System.out.println("---- Weekly Status Table ----");

        // XPath to visible list items (skip hidden ones)
        List<WebElement> statusItems = driver.findElements(
            By.xpath("//ul[contains(@class,'paidunpaid-list')]/li[not(contains(@class,'weekShow')) and .//span[starts-with(@id, 'weekCount')]]")
        );

        for (WebElement item : statusItems) {
            String statusName = item.findElement(By.xpath(".//div[contains(@class,'left-lst')]/span")).getText().trim();
            String count = item.findElement(By.xpath(".//div[contains(@class,'right-lst')]//span[starts-with(@id,'weekCount')]")).getText().trim();

            System.out.println(statusName + ": " + count);
        }

        System.out.println("------------------------------");
    }
}
