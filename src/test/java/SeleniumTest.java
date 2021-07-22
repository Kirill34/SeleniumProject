import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
public class SeleniumTest {
    private static WebDriver driver;
    private Logger logger = LogManager.getLogger(SeleniumTest.class);

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("browser", "chrome");
        System.setProperty("webdriver.gecko.driver", "C:/webdrivers/geckodriver.exe");
        driver = DriverFactory.getDriver(env.toLowerCase());
        driver.manage().window().fullscreen();
    }

    @BeforeEach
    public void setUp()
    {
        logger.info("Драйвер стартовал!");
    }

    @AfterEach
    public void setDown()
    {

        logger.info("Драйвер остановлен!");
    }

    @Test
    public void FirstTest() {
        driver.get("https://www.dns-shop.ru/");
        logger.info(driver.getTitle());
        logger.info(driver.getCurrentUrl());
        WebElement element= driver.findElement(By.xpath("//*[@id=\"homepage-desktop-menu-wrap\"]/div/div[1]/div[1]/a"));
        element.click();
        List<WebElement> categories=driver.findElements(By.className("subcategory__mobile-title"));
        for ( WebElement category:categories) {
            logger.info(category.getText());
        }

        Assert.assertTrue(true);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }
}
