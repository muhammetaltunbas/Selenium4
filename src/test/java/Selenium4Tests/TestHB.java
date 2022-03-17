package Selenium4Tests;

import org.openqa.selenium.By;
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

public class TestHB extends Base {
    boolean result = true;

    @BeforeMethod
    public void initialize() throws IOException {
        driver=initializeDriver();
    }

    @Test(priority = 0)
    public void checkNetwork() throws IOException, InterruptedException {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty())); // Bu komut ile CDP-Network alanında bulunan trafiğe erişebileceğiz.
        devTools.addListener(Network.requestWillBeSent(), request -> {
            Request req = request.getRequest();
            //System.out.println(req.getUrl());
            //req.getHeaders();
        });
        devTools.addListener(Network.responseReceived(), response -> // responseReceived() methodunu dinleyen bir listener ekledik, bu methodu'un cevabıını "response" a aldık.
                // (Not: response'a gelen datalar object tipinde.
        {
            Response res = response.getResponse();
            //System.out.println(res.getUrl());
            //System.out.println(res.getStatus());
            if (res.getStatus().toString().startsWith("4")) {
                System.out.println(res.getUrl() + "is failing with status code " + res.getStatus());
                result = false;
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Assert.assertTrue(result);
    }

    @Test(priority = 1)
    public void mockAPI() throws InterruptedException {
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), request ->
        {
            if (request.getRequest().getUrl().contains("shetty")) {
                String mockedUrl = request.getRequest().getUrl().replace("=shetty", "=BadGuy");
                //System.out.println(mockedUrl);
                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(mockedUrl), Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(), Optional.empty()));
            } else {

                devTools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(request.getRequest().getUrl()), Optional.of(request.getRequest().getMethod()),
                        Optional.empty(), Optional.empty(), Optional.empty()));
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        Thread.sleep(3000);
        String message = driver.findElement(By.cssSelector("p")).getText();
        Assert.assertEquals(message, "Oops only 1 Book available");
    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}