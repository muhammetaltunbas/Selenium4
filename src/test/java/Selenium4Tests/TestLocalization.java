package Selenium4Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

public class TestLocalization extends Base {
    boolean result = true;

    @BeforeMethod
    public void initialize() throws IOException {
        driver=initializeDriver();
    }
    
    @Test(priority = 0)
    public void checkLocalization() {
        Map location = new HashMap();
        location.put("latitude", 40);
        location.put("longitude", 3);
        location.put("accuracy", 10);
        driver.executeCdpCommand("Emulation.setGeolocationOverride", location);

        //devTools.send(Emulation.setGeolocationOverride(40,3,10);

        driver.get("http://www.google.com");
        driver.findElement(By.cssSelector("[name='q']")).sendKeys("Netflix", Keys.ENTER);
        String titleOnBrowser = driver.findElement(By.cssSelector("[class*='LC20lb']")).getText();
        String actualTitle = "Netflix España";
        if (!titleOnBrowser.contains(actualTitle))
            Assert.assertTrue(false);
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}