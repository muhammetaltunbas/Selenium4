package Selenium4Tests;

import com.google.common.collect.ImmutableList;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v96.network.Network;
import org.openqa.selenium.devtools.v96.network.model.ConnectionType;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.IOException;
import java.util.Optional;

public class TestNetworkSpeed extends Base {

    @BeforeMethod
    public void initialize() throws IOException {
        driver=initializeDriver();
    }

    @Test(priority = 0)
    public void networkSpeed() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET)));
        devTools.addListener(Network.loadingFailed(), loadingFailed ->// İnternet bağlantısının olmadığı durumda testlerin fail oluyor fakat enden faile olduğunu bilemiyorduk. ,
                // Artık Network.loadingFailed() eventi ile birlikte testlerin fail olma sebebini tam olarak bulabiliyoruz.
        {
            System.out.println(loadingFailed.getErrorText());
            System.out.println(loadingFailed.getTimestamp());
        });
        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        driver.close();
        //15075: Netwok yavaşlatılınca   1701: Network normal halinde

    }


    @Test(priority = 1)
    public void networkSpeed2() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000, Optional.of(ConnectionType.ETHERNET)));

        long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.cssSelector("button[routerlink*='library']")).click();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        //15075: Netwok yavaşlatılınca   1701: Network normal halinde

    }

    @AfterTest
    public void closeBrowser() throws InterruptedException {
        Thread.sleep(3000);
        driver.close();
    }
}