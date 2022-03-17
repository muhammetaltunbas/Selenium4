package Selenium4Tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import resources.Base;

import java.io.File;
import java.io.IOException;

public class TestgetScreenshotOfElement extends Base {

    @BeforeMethod
    public void initialize() throws IOException {
        driver = initializeDriver();
    }

    @Test(priority = 0)
    public void getScreenshot() throws IOException {
        driver.get("https://rahulshettyacademy.com/angularpractice/");
        WebElement nameArea = driver.findElement(By.cssSelector("[name='name']"));
        nameArea.sendKeys("Test Ekran Görüntüsü");

        File sourceFile = nameArea.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("name.png"));//sourcerFile-destinationFile

    }

    @Test(priority = 1)
    public void getScreenshotHB() throws IOException {
        driver.get("https://www.hepsiburada.com/");
        hoverMenu();
        //WebElement kitapMenu = driver.findElement(By.xpath("//span[contains(text(),'Kitap, Müzik, Film, Hobi')]"));
        WebElement showSubCategory = driver.findElement(By.cssSelector("[class*='sf-ChildMenuItems-2DIVP']"));

        File sourceFile = showSubCategory.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("nameHbCategory.png"));
    }

}
