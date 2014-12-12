package nl.rooftopenergy.bionic.rest;

import static org.junit.Assert.*;

import nl.rooftopenergy.bionic.transfer.ScoreDataTransfer;
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
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContextForUnitTests.xml"})
public class ScoreDataResourceTest {

    @Inject
    ScoreDataResource scoreDataResource;

    /**
     * Initialize the application context to re-use in all test cases
     * */
    @BeforeClass
    public static void setup() {
        Authentication auth = new UsernamePasswordAuthenticationToken("rooftop", "energy");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
    }

    @Test
    public void testShowProductionScore() throws Exception {
        List<ScoreDataTransfer> scoreList = scoreDataResource.showProductionScore();
        assertEquals(scoreList.get(0).getCompany(),"My School");
        //5923 rooftop
        //1380 school
        assertEquals(scoreList.get(0).getArrow(),Integer.valueOf(-1));
    }

    @Test
    public void testShowConsumptionScore() throws Exception {
        List<ScoreDataTransfer> scoreList = scoreDataResource.showConsumptionScore();
        assertEquals(scoreList.get(0).getCompany(),"My School");
        //5551 rooftop
        //520 school
        assertEquals(scoreList.get(0).getArrow(),Integer.valueOf(-1));
    }

    @Test
    public void testShowOverallScore() throws Exception {
        List<ScoreDataTransfer> scoreList = scoreDataResource.showOverallScore();
        assertEquals(scoreList.get(0).getCompany(),"My School");
        //1.067 rooftop
        //2.653 school
        assertEquals(scoreList.get(0).getArrow(),Integer.valueOf(1),0.001);
    }
}