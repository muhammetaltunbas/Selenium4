package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Base {
    public static ChromeDriver driver;
    public static WebDriverWait w;//test
    public static DevTools devTools;
    public By headerMenu = By.xpath("//span[contains(text(),'Kitap, Müzik, Film, Hobi')]");
    public By productLink = By.xpath("//a[@href='https://www.hepsiburada.com/uzaktan-kumandali-arabalar-c-14001244']");
    private Properties prop;
    private JavascriptExecutor js;

    public Properties getProp() {
        return prop;
    }

    public JavascriptExecutor getJS() {
        js = (JavascriptExecutor) driver;
        return js;
    }

    public ChromeDriver initializeDriver() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(
                System.getProperty("user.dir") + "//src//main//java//resources//data.properties");
        prop.load(fis);
        String browserName = prop.getProperty("browser");// data.properties
        if (browserName.contains("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            devTools = driver.getDevTools();
            devTools.createSession();
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.manage().deleteAllCookies();

        return driver;
    }

    public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationFile = System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
        FileUtils.copyFile(source, new File(destinationFile));
        return destinationFile;

    }

    public void waitFunctionClickable(By locator) { //Explicit Wait
        w = new WebDriverWait(driver, Duration.ofSeconds(10)); //w = new WebDriverWait(driver, 10); => old
        //WebDriverWait wait = new WebDriverWait(driver,10);
        w.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public WebElement headerMenu() {
        return driver.findElement(headerMenu);
    }

    public void hoverMenu() {
        Actions actions = new Actions(driver);// mouse hover
        actions.moveToElement(headerMenu()).build().perform();
        waitFunctionClickable(productLink);
    }


}