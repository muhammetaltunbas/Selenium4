package Selenium4Tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.emulation.Emulation;
import org.openqa.selenium.devtools.v96.fetch.Fetch;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.Request;
import org.openqa.selenium.devtools.v96.network.model.Response;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestAPIStatusCode extends Base {
    boolean result = true;

    @BeforeMethod
    public void initialize() throws IOException {
        driver=initializeDriver();
    }

    @Test(priority = 0)
    public void checkNetwork(){
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty())); // Bu komut ile CDP-Network alanında bulunan trafiğe erişebileceğiz.
        devTools.addListener(Network.responseReceived(), response -> // responseReceived() methodunu dinleyen bir listener ekledik, bu methodu'un cevabıını "response" a aldık. (Not: response'a gelen datalar object tipinde.w
        {
            Response res = response.getResponse();
            if (res.getStatus().toString().startsWith("4")) {
                System.out.println(res.getUrl() + "is failing with status code " + res.getStatus());
                result = false;
            }
        });
        driver.get("https://www.hepsiburada.com/");
        hoverMenu();
        driver.findElement(productLink).click();
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}