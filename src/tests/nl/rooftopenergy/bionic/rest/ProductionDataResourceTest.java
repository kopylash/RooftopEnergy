package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.transfer.GraphDataTransfer;
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
import java.sql.Timestamp;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContextForUnitTests.xml"})
public class ProductionDataResourceTest {

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    ProductionDataResource resource;


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
    public void testDailyProduction() throws Exception {
        long date = Timestamp.valueOf("2014-12-01 03:00:00").getTime();
        List<GraphDataTransfer> list = resource.dailyProduction(String.valueOf(date));
        assertNotNull(list);

    }

    @Test
    public void testMonthlyProduction() throws Exception {
        long date = Timestamp.valueOf("2014-12-01 03:00:00").getTime();
        List<GraphDataTransfer> list = resource.monthlyProduction(String.valueOf(date));
        assertNotNull(list);

    }

    @Test
    public void testYearlyProduction() throws Exception {
        long date = Timestamp.valueOf("2014-12-01 03:00:00").getTime();
        List<GraphDataTransfer> list = resource.yearlyProduction(String.valueOf(date));
        assertNotNull(list);

    }

    @Test
    public void testTotallyProduction() throws Exception {
        long date = Timestamp.valueOf("2014-12-07 03:00:00").getTime();
        List<GraphDataTransfer> list = resource.totallyProduction(String.valueOf(date));
        assertNotNull(list);

    }
}