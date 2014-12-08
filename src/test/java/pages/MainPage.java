package pages;

import selenium.WebDriverWrapper;

/**
 * Created by Time To on 08.12.2014.
 */
public class MainPage extends BasePage {

    protected String url = "http://tomcat-rooftopenergy.rhcloud.com/loggedPage.html";

    public MainPage(WebDriverWrapper driver) {
        super(driver);
    }
}
