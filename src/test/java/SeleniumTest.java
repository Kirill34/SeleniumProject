import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;
public class SeleniumTest {
    private static WebDriver driver;
    @BeforeClass
    public static void setup() {
        System.setProperty("webdriver.gecko.driver", "C:/webdrivers/geckodriver.exe");
        driver = DriverFactory.getDriver(DriverType.FIREFOX);

    }

    @Test
    public void FirstTest() {
        driver.get("https://www.dns-shop.ru/");
        Assert.assertTrue(true);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
