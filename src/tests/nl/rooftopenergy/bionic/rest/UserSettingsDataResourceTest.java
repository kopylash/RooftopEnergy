package nl.rooftopenergy.bionic.rest;

import static org.junit.Assert.*;

import nl.rooftopenergy.bionic.rest.UserSettingsDataResource;
import nl.rooftopenergy.bionic.transfer.PasswordTransfer;
import nl.rooftopenergy.bionic.transfer.UserDataTransfer;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

/**
 * Created by Bess on 12/17/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContextForUnitTests.xml"})
public class UserSettingsDataResourceTest {

    @Inject
    private UserSettingsDataResource resource;

    @BeforeClass
    public static void setup() {
        Authentication auth = new UsernamePasswordAuthenticationToken("marat", "qwerty");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
    }

    /*Test works only for the current DB state (12/18/14).
     * It will fail if the data in db will be changed.
     * Current values are shown in comments in every method
     */
    @Ignore
    @Test
    public void testSaveNewDescription() throws Exception {
        throw new Exception("test doesn't work properly");
      /*  resource.saveNewDescription("testDescription");
        UserDataTransfer userDataTransfer = resource.getUserDescription();
        assertEquals(userDataTransfer.getDescription(), "testDescription");
        assertEquals(userDataTransfer.getCity(), "Rotterdam");
        assertEquals(userDataTransfer.getCompany(), "Rooftop");
        assertEquals(userDataTransfer.getCountry(), "Energy");
        assertEquals(userDataTransfer.getProvince(), "South Holland");
        assertEquals(userDataTransfer.getStreet(), "Borge");
        assertEquals(userDataTransfer.getUserName(), "rooftop");
        assertEquals(userDataTransfer.getPanelType(), "The school panel");


        //In order to return DB in the previous state;
        resource.saveNewDescription("Some description");*/
    }

    @Test
    public void testChangeWrongPassword() throws Exception {
        //test for incorrect data
        PasswordTransfer wrongPasswords = new PasswordTransfer("1","qwerty1");
        Response response=resource.changePassword(wrongPasswords);
        assertEquals(304,response.getStatus());

    }

    @Test
    public void testChangeRightPassword() throws Exception {
        //test work of method
        PasswordTransfer correctPasswords = new PasswordTransfer("qwerty","1");
        Response response=resource.changePassword(correctPasswords);
        assertEquals(200,response.getStatus());

        //return previous DB state
        PasswordTransfer returnPasswords = new PasswordTransfer("1","qwerty");
        resource.changePassword(returnPasswords);
    }


}
