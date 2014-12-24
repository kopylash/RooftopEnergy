package nl.rooftopenergy.bionic.rest;

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

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContextForUnitTests.xml"})
public class EcoDataResourceTest {

    @Inject
    private EcoDataResource ecoDataResource;

    /**
     * Initialize the application context to re-use in all test cases
     */
    @BeforeClass
    public static void setup() {
        Authentication auth = new UsernamePasswordAuthenticationToken("rooftop", "energy");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
    }

    /*Test works only for the current DB state.
    * It will fail if the data in db will be changed.
    * Current values are totalProduction=5923 W*h
    */
    @Test
    public void testGetTreesSaved() throws Exception {
        Double result = ecoDataResource.getTreesSaved();
        assertEquals(0.0162,result,0.0001);
    }

    /*Test works only for the current DB state.
    * It will fail if the data in db will be changed.
    * Current values are totalProduction=5923 W*h
    */
    @Test
    public void testGetCarbonOffset() throws  Exception {
        Double result = ecoDataResource.getCarbonOffset();
        assertEquals(2.724,result,0.001);
    }
}