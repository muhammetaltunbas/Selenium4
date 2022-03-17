package Selenium4Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.v96.emulation.Emulation;
import org.openqa.selenium.devtools.v96.fetch.Fetch;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.Request;
import org.openqa.selenium.devtools.v96.network.model.Response;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestMobileEmulator extends Base {
    boolean result = true;

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test(priority = 0)
    public void checkMobileEmulator() throws IOException, InterruptedException {
        //SeleniumCommand
        devTools.send(Emulation.setDeviceMetricsOverride(600, 1000, 50, true,
                Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(), Optional.empty(),
                Optional.empty(), Optional.empty(), Optional.empty()));
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        WebElement options = driver.findElement(By.cssSelector("[href*='library']"));
        driver.findElement(By.cssSelector("[class*='navbar-toggler-icon']")).click();
        Thread.sleep(1000);
        Assert.assertTrue(options.isDisplayed());
    }

    @Test(priority = 1)
    public void checkMobileEmulator2() throws IOException, InterruptedException {
        //ExecuteCdpCommand
        Map deviceMetrics = new HashMap() {
            {
                put("width", 600);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 100);
            }
        };
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("[class*='navbar-toggler-icon']")).click();
        WebElement options = driver.findElement(By.cssSelector("[href*='library']"));
        Thread.sleep(1000);
        Assert.assertTrue(options.isDisplayed());
    }


    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}