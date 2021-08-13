import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        driver.manage().addCookie(new Cookie("cookie2","value2"));
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
        logger.info( driver.manage().getCookies());
    }

    @Test
    public void SecondTest()
    {
        driver.get("https://www.dns-shop.ru/");
        driver.manage().window().fullscreen();
        String buttonok = "//a[normalize-space(text())='Да']";
        WebElement elementok = driver.findElement(By.className("btn-additional"));
        logger.info("WebElement: " + elementok.getTagName() + " = " + elementok.getText());
        elementok.click();
        logger.info("Закрыто подтверждение города");
        WebElement smartphones_and_gadgets = driver.findElement(By.linkText("Смартфоны и гаджеты"));
        Actions actions = new Actions(driver);
        actions.moveToElement(smartphones_and_gadgets).build().perform();
        WebElement smartphone_link = driver.findElement(By.xpath("//*[@id=\"homepage-desktop-menu-wrap\"]/div/div[2]/div[2]/div[1]/a[1]"));
        smartphone_link.click();
        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image, "png", new File("screenshot.png"));
            Assert.assertTrue(true);
        }
        catch (java.io.IOException e)
        {
            logger.info("Не удалось сделать скриншот");
        }
        //WebElement samsung = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/div[3]/div[1]/div[5]/div/div/div[2]/label[20]/span[2]"));
        WebElement allProducts = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/div[3]/div[1]/div[1]/div/div/div[2]/label[3]/span"));
        allProducts.click();
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class);
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,800);");
        WebElement samsung = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver)
            {
                return driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/div[3]/div[1]/div[5]/div/div/div[2]/label[20]/span[2]"));
            }
        });
       // ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",samsung);
        samsung.click();

        WebElement memory = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/div[3]/div[1]/div[7]/a"));
        //((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",memory);
        memory.click();
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,1000);");

        WebElement gb8 = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/div[3]/div[1]/div[7]/div/div/div[2]/label[2]"));
        gb8.click();
        WebElement apply_btn = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/div[3]/div[2]/div/button[1]"));
        apply_btn.click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {

        }

        WebElement sortVariant = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/div[2]/div[1]/div[2]/a/span[1]"));
        sortVariant.click();
        WebElement firstExpensive = driver.findElement(By.xpath("/html/body/div[5]/div/label[2]/span"));
        firstExpensive.click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e)
        {

        }
        WebElement firstItem = driver.findElement(By.className("catalog-product__name"));
        String href=firstItem.getAttribute("href");
        driver.get(href);
        try {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            BufferedImage image = ImageIO.read(file);
            ImageIO.write(image, "png", new File("screenshot2.png"));
            Assert.assertTrue(true);
        }
        catch (java.io.IOException e)
        {
            logger.info("Не удалось сделать скриншот");
        }
       // firstItem.click();
        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {

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
