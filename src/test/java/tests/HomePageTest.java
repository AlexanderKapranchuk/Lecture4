package tests;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Properties;

public class HomePageTest {
    public static WebDriver driver;
    public static Properties prop;


    @BeforeTest
    public static void initialization() {
        String browserName = prop.getProperty("browser");

        if (browserName.equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "//src/test/" + "resources/drivers/chromedriver86.exe");
            driver = new ChromeDriver();
        } else if (browserName.equals("FF")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//src/test/resources/drivers/gecko.exe");
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @Test
    public void test1() {
        //driver.getTitle(); "prestashop-automation"
        driver.get(prop.getProperty("url"));
        System.out.println("Page titel is: " + driver.getTitle());
        Assert.assertEquals(driver.getTitle(), "prestashop-automation");

    }

    @Test
    public void test2() {

    }

    @AfterTest
    public void TestAfter() {
        System.out.println("Test is over");
        driver.quit();
    }
}
