package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Log4Test;

import java.util.List;
import java.util.Set;

/**
 *
 */
public class WebDriverWrapper implements WebDriver {

    private WebDriver driver;

    public WebDriverWrapper (WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String s) {
        driver.get(s);
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public String getTitle() {
        return driver.getTitle();
    }

    @Override
    public List<WebElement> findElements(By by) {

        int i = 0;

        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            i++;
        }   catch (NoSuchElementException e) {
            Log4Test.error("BUG!!! Rerun test!");
        }

        if (i > 0)
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        int i = 0;

        WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
            i++;
        } catch (NoSuchElementException e) {
            Log4Test.error("BUG!!! Rerun test.");
        }
        if (i > 0)
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        return driver.getPageSource();
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void quit() {
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    @Override
    public TargetLocator switchTo() {
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        return driver.navigate();
    }

    @Override
    public Options manage() {
        return driver.manage();
    }
}
