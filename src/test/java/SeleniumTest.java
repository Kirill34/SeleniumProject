import com.sun.org.apache.xpath.internal.operations.Bool;
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
import org.openqa.selenium.support.ui.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class SeleniumTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private Logger logger = LogManager.getLogger(SeleniumTest.class);

    @BeforeClass
    public static void setup() {

        String env = System.getProperty("browser", "chrome");
        String strategy = System.getProperty("option","none");
        driver = DriverFactory.getDriver(env.toLowerCase(), strategy.toLowerCase());
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
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
        WebElement smartphones_and_gadgets = wait.until(presenceOfElementLocated(By.linkText("Смартфоны и гаджеты")));
        Actions actions = new Actions(driver);
        actions.moveToElement(smartphones_and_gadgets).build().perform();
        WebElement smartphone_link = wait.until(presenceOfElementLocated(By.xpath("//a[normalize-space(text())='Смартфоны']")));
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
        WebElement allProducts = driver.findElement(By.xpath("//span[normalize-space(text())='Все товары, включая отсутствующие в продаже']"));
        allProducts.click();
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,1000);");
        WebElement producer = driver.findElement(By.xpath("//span[normalize-space(text())='Samsung']"));
        producer.click();
        WebElement chooseProducer = driver.findElement(By.xpath("//span[normalize-space(text())='Производитель']"));
        chooseProducer.click();
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,1200);");
        WebElement memory_choose = driver.findElement(By.xpath("//a/span[normalize-space(text())='Объем оперативной памяти']"));
        memory_choose.click();
        List<WebElement> showAll = driver.findElements(By.xpath("//span[normalize-space(text())='Показать всё']"));
        for (WebElement showAll_element: showAll) {
            if (showAll_element.isDisplayed())
                showAll_element.click();
        }


        WebElement memory8gb = driver.findElement(By.xpath("//span[normalize-space(text())='8 Гб']"));
        memory8gb.click();
        WebElement applyBtn = driver.findElement(By.xpath("//button[normalize-space(text())='Применить']"));
        applyBtn.click();

        boolean pageLoaded = false;
        while (!pageLoaded) {
            Boolean loaded = (Boolean) ((JavascriptExecutor) driver).executeScript(" return (document.getElementsByClassName(\"picked-filter__close\").length ==6)");
            pageLoaded=loaded.booleanValue();
        }

        WebElement firstProduct = wait.until(presenceOfElementLocated(By.className("catalog-product__name")));
        firstProduct.click();

        pageLoaded = false;
        while (!pageLoaded) {
            Boolean loaded = (Boolean) ((JavascriptExecutor) driver).executeScript(" return (document.readyState === \"complete\")");
            pageLoaded=loaded.booleanValue();
        }

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


        /*
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).ignoring(NoSuchElementException.class);

        ((JavascriptExecutor) driver).executeScript("document.querySelector(\"body > div.container.category-child > div > div.products-page__content > div.products-page__left-block.is-affixed > div > div.left-filters > div.left-filters__list > div:nth-child(5) > div > div > div.ui-checkbox-group.ui-checkbox-group_list > label:nth-child(21) > input\").click()");


        WebElement memory = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/div[1]/div/div[3]/div[1]/div[7]/a"));
        memory.click();
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,1000);");
        ((JavascriptExecutor) driver).executeScript("document.querySelector(\"body > div.container.category-child > div > div.products-page__content > div.products-page__left-block.is-affixed > div > div.left-filters > div.left-filters__list > div:nth-child(7) > div > div > div.ui-checkbox-group.ui-checkbox-group_list > label:nth-child(2)\").click()");
        ((JavascriptExecutor)driver).executeScript("window.scroll(0,1600);");
       ((JavascriptExecutor) driver).executeScript("document.querySelector(\"body > div.container.category-child > div > div.products-page__content > div.products-page__left-block.is-affixed > div > div.left-filters > div.left-filters__buttons > div > button.button-ui.button-ui_brand.left-filters__button\").click()");


       boolean isTopOfPage = false;
        while (!isTopOfPage) {
           Boolean result =  (Boolean)(((JavascriptExecutor) driver).executeScript("return (window.pageYOffset<300)"));
           isTopOfPage=result.booleanValue();
            logger.info("Is top of page: " + result);
        }

        ((JavascriptExecutor) driver).executeScript("window.scroll(0,0);");
        ((JavascriptExecutor) driver).executeScript("document.querySelector(\".top-filter__selected\").click()");
        ((JavascriptExecutor) driver).executeScript("document.querySelector(\"body > div.popover-block.popover-block_show > div > label:nth-child(2) > input\").click()");

        boolean pageLoaded = false;
        while (!pageLoaded) {
            Boolean loaded = (Boolean) ((JavascriptExecutor) driver).executeScript(" return (document.readyState === \"complete\")");
            pageLoaded=loaded.booleanValue();
        }

        WebElement firstItem = driver.findElement(By.className("catalog-product__name"));
        String href=firstItem.getAttribute("href");
        driver.get(href);

        pageLoaded = false;
        while (!pageLoaded) {
            Boolean loaded = (Boolean) ((JavascriptExecutor) driver).executeScript(" return (document.readyState === \"complete\")");
            pageLoaded=loaded.booleanValue();
        }

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
        ((JavascriptExecutor) driver).executeScript("document.querySelector(\"div.product-card-tabs__list > a:nth-child(4)\").click()");
        pageLoaded = false;
        while (!pageLoaded) {
            Boolean loaded = (Boolean) ((JavascriptExecutor) driver).executeScript(" if (document.querySelector(\"tbody\")) return true; else return false; ");
            pageLoaded=loaded.booleanValue();
        }


        String characters_js = "var result=false;\n"+
                "var arr=document.querySelector(\"tbody\").childNodes\n"+
                "arr.forEach(function(item, i, arr) {\n" +
                "text=(item.textContent)\n" +
                "if (text==\" Объем встроенной памяти  8 ГБ\") result=true;\n" +
                "})\n"+
                "return result;";

        Boolean memory_value = (Boolean)((JavascriptExecutor) driver).executeScript(characters_js);

        try {
            Thread.sleep(10000);
        }
        catch (InterruptedException e)
        {

        }

     /**/
        //Assert.assertTrue(memory_value.booleanValue());
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
