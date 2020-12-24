package tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class HomePageTest {

    public static WebDriver driver;
    public static Properties prop;

    public HomePageTest() {
        try {
            prop = new Properties();
            FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "/src/test/" + "java/utils/config.properties");
            prop.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeTest
    public static void initialization() {
        System.out.println("Start Test");
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src/test/" + "resources/drivers/chromedriver87.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("FF")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src/test/resources/drivers/gecko.exe");
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test(priority = 1)
    public void test1() {
        driver.get(prop.getProperty("url"));
        System.out.println("Page titel is: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "prestashop-automation");

    }

    @Test(priority = 2)
    public void test2() {
        WebElement element = driver.findElement(By.name("s"));
        element.sendKeys("dress");
        element.submit();

        WebDriverWait waitDress = new WebDriverWait(driver, 5);
        waitDress.until(ExpectedConditions.visibilityOfElementLocated
                (By.xpath("//*[@id=\"main\"]/h2")));

        WebElement product = driver.findElement(By.xpath("//a[contains(text(),'Faded Short Sleeve T-shirts')]"));
        WebElement productLink = driver.findElement(By.cssSelector(".quick-view"));

        Actions actions = new Actions(driver);
        actions.moveToElement(product).pause(Duration.ofSeconds(3)).click(productLink).build().perform();

    }

    @Test(priority = 3)
     public void test3(){


    }



    @AfterTest
    public void quit() {
        System.out.println("Test is over");
       // driver.quit();
    }
}
