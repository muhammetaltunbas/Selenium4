package Selenium4Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WindowType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;
import java.util.ArrayList;

public class TestMultipleWindowsTabs extends Base {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test(priority = 0)
    public void beforeSelenium4() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");

        driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
        JavascriptExecutor jse = (JavascriptExecutor) driver;//opens new tab
        jse.executeScript("window.open()");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.get("https://rahulshettyacademy.com/#/index");
        String courseName = driver.findElements(By.cssSelector("[href*='https://courses.rahulshettyacademy.com/p/']")).get(1).getText();

        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.cssSelector("[name='name']")).sendKeys(courseName);
    }

    @Test(priority = 1)
    public void afterSelenium4() {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        driver.switchTo().newWindow(WindowType.TAB); //driver.switchTo().newWindow(WindowType.WINDOW);

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

        driver.get("https://rahulshettyacademy.com/#/index");
        String courseName = driver.findElements(By.cssSelector("[href*='https://courses.rahulshettyacademy.com/p/']")).get(1).getText();

        driver.switchTo().window(tabs.get(0));
        driver.findElement(By.cssSelector("[name='name']")).sendKeys(courseName);
    }

    @Test(priority = 2)
    public void selenium3() {
        driver.get("https://www.tutorialspoint.com/index.htm");

        JavascriptExecutor jse = (JavascriptExecutor) driver;//opens new tab
        jse.executeScript("window.open()");

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        driver.get("https://www.tutorialspoint.com/online_dev_tools.htm");
        driver.findElement(By.cssSelector("[data-icon*='location']")).click();
    }


    @Test(priority = 3)
    public void selenium4() {
        driver.get("https://www.tutorialspoint.com/index.htm");

        driver.switchTo().newWindow(WindowType.TAB);

        driver.get("https://www.tutorialspoint.com/online_dev_tools.htm");
        driver.findElement(By.cssSelector("[data-icon*='location']")).click();

    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.quit();
        //driver.close();
    }
}