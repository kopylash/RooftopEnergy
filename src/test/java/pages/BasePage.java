package pages;

import selenium.WebDriverWrapper;

/**
 * Created by Time To on 08.12.2014.
 */
public class BasePage {

    WebDriverWrapper driver;
    private String url;

    public BasePage(WebDriverWrapper driver){
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
