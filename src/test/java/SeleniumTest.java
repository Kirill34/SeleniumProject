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
import java.util.Locale;
import java.util.concurrent.TimeUnit;
public class SeleniumTest {
    private static WebDriver driver;
    private Logger logger = LogManager.getLogger(SeleniumTest.class);

    @BeforeClass
    public static void setup() {

        String env = System.getProperty("browser", "chrome");
        String strategy = System.getProperty("option","none");
        System.setProperty("webdriver.gecko.driver", "C:/webdrivers/geckodriver.exe");
        driver = DriverFactory.getDriver(env.toLowerCase(), strategy.toLowerCase());
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
        driver.manage().window().fullscreen();
        logger.info(driver.getTitle());
        logger.info(driver.getCurrentUrl());

        String buttonok = "//a[normalize-space(text())='Да']";
        WebElement elementok = driver.findElement(By.className("btn-additional"));
        logger.info("WebElement: " + elementok.getTagName() + " = " + elementok.getText());
        elementok.click();
        logger.info("Закрыто подтверждение города");
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {

        }
        WebElement element= null;
        element=driver.findElement(By.className("menu-desktop__root-title"));
        element.click();
        List<WebElement> categories=driver.findElements(By.className("subcategory__item"));
        for ( WebElement category:categories) {
            logger.info(category.getText());
        }

        Assert.assertTrue(true);
    }

    @AfterClass
    public static void tearDown() {
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {

        }
        driver.quit();
    }
}
