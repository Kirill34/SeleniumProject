import com.google.common.base.Supplier;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.HashMap;
import java.util.Map;

enum DriverType {
    CHROME,
    FIREFOX,
    SAFARI,
    IE;
}

public class DriverFactory {

    private static final Map<DriverType, Supplier<WebDriver>> driverMap = new HashMap<>();

    //chrome driver supplier
    private static final Supplier<WebDriver> chromeDriverSupplier = () -> {
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("incognito");
        return new ChromeDriver(options);
    };

    //firefox driver supplier
    private static final Supplier<WebDriver> firefoxDriverSupplier = () -> {
        System.setProperty("webdriver.gecko.driver", "C:/webdrivers/geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        return new FirefoxDriver(options);
    };

    //add more suppliers here

    //add all the drivers into a map
    static{
        driverMap.put(DriverType.CHROME, chromeDriverSupplier);
        driverMap.put(DriverType.FIREFOX, firefoxDriverSupplier);
    }

    //return a new driver from the map
    public static final WebDriver getDriver(DriverType type){
        return driverMap.get(type).get();
    }

}