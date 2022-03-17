package Selenium4Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import resources.Base;
import java.io.IOException;

import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class TestRelativeLocators extends Base {

    @BeforeTest
    public void initialize() throws IOException {
        driver = initializeDriver();
        driver.get("https://rahulshettyacademy.com/angularpractice/");
    }

    @Test(priority = 0)
    public void above() {
        WebElement nameBox = driver.findElement(By.cssSelector("[name='name"));
        String nameLabel = driver.findElement(with(By.tagName("label")).above(nameBox)).getText();
        System.out.println("Name Label: " + nameLabel);
    }

    @Test(priority = 1)
    public void below() {
        WebElement dateofBirthText = driver.findElement(By.cssSelector("[for='dateofBirth']"));
        driver.findElement(with(By.tagName("input")).below(dateofBirthText)).click();
    }

    @Test(priority = 2)
    public void left() {
        WebElement iceCreamText = driver.findElement(By.cssSelector("[for='exampleCheck1']"));
        driver.findElement(with(By.tagName("input")).toLeftOf(iceCreamText)).click();
    }

    @Test(priority = 3)
    public void rigt() {
        WebElement studentRadio = driver.findElement(By.id("inlineRadio1"));
        String studentLabel = driver.findElement(with(By.tagName("label")).toRightOf(studentRadio)).getText();
        System.out.println("Student Label: " + studentLabel);
    }

    @AfterTest
    public void closeBrowser() {
        //driver.close();
    }

}

