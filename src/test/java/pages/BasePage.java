package pages;

import org.openqa.selenium.WebDriver;

/**
 * Created by Time To on 08.12.2014.
 */
public class BasePage {

    WebDriver driver;
    private String url = "http://tomcat-rooftopenergy.rhcloud.com/loginPage.html";

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void open () {
        driver.get(url);
    }

    public void open (String url) {
        driver.get(url);
    }

    public boolean isOpened(){
        return driver.getCurrentUrl().equals(url);
    }
}
