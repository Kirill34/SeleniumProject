import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.CapabilityType;

public class DriverFactory {
    private static Logger logger = LogManager.getLogger(DriverFactory.class);

    public static WebDriver getDriver(String browserName, String strategy) {
        PageLoadStrategy plStrategy = PageLoadStrategy.EAGER;
        switch (strategy)
        {
            case "none":
                plStrategy=PageLoadStrategy.NONE;
                break;
            case "normal":
                plStrategy=PageLoadStrategy.NORMAL;
                break;
            case "eager":
                plStrategy=PageLoadStrategy.EAGER;
                break;
        }
        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                logger.info("Драйвер для браузера Google Chrome");
                ChromeOptions chOptions = new ChromeOptions();
                chOptions.addArguments("incognito");
                chOptions.setPageLoadStrategy(plStrategy);
                chOptions.addArguments("--start-maximized");
                chOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                chOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
                return new ChromeDriver(chOptions);
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                logger.info("Драйвер для браузера Mozilla Firefox");
                FirefoxOptions fOptions = new FirefoxOptions();
                fOptions.addArguments("-private");
                fOptions.setPageLoadStrategy(plStrategy);
                fOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
                fOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
                return new FirefoxDriver(fOptions);
            case "edge" :
                WebDriverManager.edgedriver().setup();
                logger.info("Драйвер для браузера Microsoft Edge");
                return new EdgeDriver();
            case "explorer" :
                WebDriverManager.iedriver().setup();
                logger.info("Драйвер для браузера Microsoft Internet Explorer");
                return new InternetExplorerDriver();
            case "opera" :
                WebDriverManager.operadriver().setup();
                logger.info("Драйвер для браузера Opera");
                return new OperaDriver();

            default:
                throw new RuntimeException("Incorrect browser name");
        }
    }
}