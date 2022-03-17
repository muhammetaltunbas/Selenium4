package Selenium4Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.File;
import java.io.IOException;

public class TestgetWidthHeightElement extends Base {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test
    public void getHeightandWidth() throws IOException {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement nameArea = driver.findElement(By.cssSelector("[name='name']"));
        nameArea.sendKeys("Test Ekran Görüntüsü");

        int heightofElement = nameArea.getRect().getDimension().getHeight();
        int widthofElement = nameArea.getRect().getDimension().getWidth();
        System.out.println("Height of Element: " + heightofElement);
        System.out.println("Width of Element: " + widthofElement);
        Assert.assertEquals(38, heightofElement);
        Assert.assertEquals(1110, widthofElement);

    }
    @Test
    public void testSize()  { //logo size
        driver.get("https://www.hepsiburada.com/");
        WebElement titleHB = driver.findElement(By.cssSelector("[title='Hepsiburada']"));
        int heightTitle=titleHB.getRect().getDimension().height;
        int widthTitle=titleHB.getRect().getDimension().width;
       int x= titleHB.getSize().getWidth();
       System.out.println(widthTitle + " ->" + x);
        Assert.assertEquals(191, x);

    }



}
