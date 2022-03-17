package Selenium4Tests;

import com.google.common.collect.ImmutableList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.fetch.Fetch;
import org.openqa.selenium.devtools.v96.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.ErrorReason;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestBlockNetwork extends Base {

    @BeforeMethod
    public void initialize() throws IOException {
            driver=initializeDriver();
    }

    @Test(priority = 0)
    public void blockNetwork() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();
        driver.findElement(By.cssSelector(".add-to-cart")).click();
        System.out.println(driver.findElement(By.cssSelector("p")).getText());
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //1793 2033
    }


    @Test(priority = 1)
    public void blockNetworkHB() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        //devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg", "*.css")));
        long startTime = System.currentTimeMillis();
        driver.get("https://www.hepsiburada.com/");
        driver.findElement(By.cssSelector("[title*='Siparişlerim']")).click();

        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //Block -> 5055 ms  No Block-> 6953 ms
        //6855 7047
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(5000);
        driver.close();
    }
}