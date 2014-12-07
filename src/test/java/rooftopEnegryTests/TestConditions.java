package rooftopEnegryTests;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import selenium.DriverFactory;
import selenium.WebDriverWrapper;
import utils.PropertyLoader;

/**
 *
 */
public class TestConditions {
    public static WebDriverWrapper driver;

    @BeforeSuite
    public void BeforeSuite () {
        driver = DriverFactory.initDriver(PropertyLoader.loadProperty("browser.name"));
        driver.manage().window().maximize();
    }

    @AfterSuite
    public void AfterSuite() {
        driver.quit();
    }
//end.
}
