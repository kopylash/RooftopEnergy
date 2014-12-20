package nl.rooftopenergy.bionic.transfer;

import static org.junit.Assert.*;

import nl.rooftopenergy.bionic.rest.UserSettingsDataResource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.inject.Inject;

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
        Authentication auth = new UsernamePasswordAuthenticationToken("rooftop", "energy");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
    }

    /*Test works only for the current DB state (12/18/14).
     * It will fail if the data in db will be changed.
     * Current values are shown in comments in every method
     */
    @Test
    public void testSaveNewDescription() {
        resource.saveNewDescription("testDescription");
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
        resource.saveNewDescription("Some description");
    }
}
