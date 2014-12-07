package selenium;

import utils.PropertyLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import utils.Log4Test;

import java.io.File;

/**
    Choosing a browser to run tests
 */
public class DriverFactory {
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    public static WebDriverWrapper initDriver(String broserName)
    {
        WebDriver driver = null;
        if (broserName.equals(FIREFOX))
        {
            driver = new FirefoxDriver();
        } else
        if (broserName.equals(CHROME))
        {
            File file = new File(PropertyLoader.loadProperty("selenium.chrome.driver.path"));
            System.setProperty("webdriver.chrome.driver",file.getAbsolutePath());
            driver = new ChromeDriver();
        } else
            Assert.fail(Log4Test.error("WebDriver not defined"));
        return new WebDriverWrapper(driver);
    }
}
