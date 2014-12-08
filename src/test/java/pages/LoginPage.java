package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by Time To on 08.12.2014.
 */
public class LoginPage extends BasePage{

    protected String url = "http://tomcat-rooftopenergy.rhcloud.com/loginPage.html";
    private static final By CONFIRM = By.id("subButton");
    private static final By lOGININPUT = By.id("login1");
    private static final By PASSWORDINPUT = By.id("password1");

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void loginIntup (String login) {
        driver.findElement(lOGININPUT).clear();
        driver.findElement(lOGININPUT).sendKeys(login);
    }

    public void passwordInput (String password) {
        driver.findElement(PASSWORDINPUT).clear();
        driver.findElement(PASSWORDINPUT).sendKeys(password);
    }

    public String confirm () {
        driver.findElement(CONFIRM).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return url = driver.getCurrentUrl();
    }
}
