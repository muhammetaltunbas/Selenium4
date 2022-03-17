package Selenium4Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;


public class TestConsoleLogCapture extends Base {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test(priority = 0)
    public void getConsoleLog() {
        //listeners - OnTestFailure
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

        LogEntries entry = driver.manage().logs().get(LogType.BROWSER);//Get LogEntries object
        List<LogEntry> logs = entry.getAll(); //LogEntry object- getAll method return all logs in list

        for (int i = 0; i < logs.size(); i++) {
            System.out.println(logs.get(i));
        }
        //Aşağıdaki diğer yöntem ile List array elementlerini yazdırma işlemi. Bunu alış bnce
        /*for(LogEntry e : logs)//iterating through list and printing each log message
        {
            System.out.println(e.getMessage());  //Log4j

        }*/

    }

    @Test(priority = 0)
    public void checkConsoleLog() {
        //listeners - OnTestFailure - Best Practice-> When case is fail, get console log.

        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.partialLinkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        driver.findElement(By.linkText("Cart")).click();
        driver.findElement(By.id("exampleInputEmail1")).clear();
        driver.findElement(By.id("exampleInputEmail1")).sendKeys("2");

        LogEntries entry = driver.manage().logs().get(LogType.BROWSER);//Get LogEntries object
        List<LogEntry> logs = entry.getAll(); //LogEntry object- getAll method return all logs in list
        for (LogEntry e : logs) {
            System.out.println(e.getMessage());  //Log4j
        }

    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}