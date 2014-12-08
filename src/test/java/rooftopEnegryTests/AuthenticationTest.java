package rooftopEnegryTests;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.MainPage;

/**
 * Created by Time To on 08.12.2014.
 */
public class AuthenticationTest extends TestConditions {

    private String url = "http://tomcat-rooftopenergy.rhcloud.com/loginPage.html";

    @DataProvider(name = "auth")
        public Object[][] provideData() {
            return new Object[][]{
                    {"rooftop", "energy"},
                    {"school", "student"}
            };
    }

    @Test(dataProvider = "auth")
    public void authenticationTest(String login, String password) {

        LoginPage loginpage = new LoginPage(driver);
        MainPage mainpage = new MainPage(driver);

        loginpage.open(url);
        loginpage.loginIntup(login);
        loginpage.passwordInput(password);
        loginpage.confirm();
        Assert.assertTrue(mainpage.isOpened());
    }
}
