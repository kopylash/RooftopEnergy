package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.transfer.RadiationTransfer;
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
public class RadiationResourceTest {

    @Inject
    private RadiationResource radiationResource;

    /**
     * Initialize the application context to re-use in all test cases
     * */
    @BeforeClass
    public static void setup() {
        Authentication auth = new UsernamePasswordAuthenticationToken("target", "qwerty");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
    }
    @Test
    public void testRadiationForYear() throws Exception {
        RadiationTransfer transfer = radiationResource.radiationForYear("45");
        assertNotNull(transfer);
        assertTrue(transfer.getData().size() == 365);

    }

    @Test
    public void testRadiationForDay() throws Exception {
        RadiationTransfer transfer = radiationResource.radiationForDay("45", "180");
        assertNotNull(transfer);
        assertTrue(transfer.getData().size() == 1);

    }
}