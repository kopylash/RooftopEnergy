package nl.rooftopenergy.bionic.rest;

import static org.junit.Assert.*;

import nl.rooftopenergy.bionic.dao.rtfbox.RtfBoxDao;
import nl.rooftopenergy.bionic.dao.rtfboxdata.RtfBoxDataDao;
import nl.rooftopenergy.bionic.entity.RtfBox;
import nl.rooftopenergy.bionic.entity.RtfBoxData;
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
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContextForUnitTests.xml"})
public class ConsumptionDataResourceTest {

    @Inject
    private RtfBoxDataDao rtfBoxDataDao;

    @Inject
    private RtfBoxDao rtfBoxDao;

    @Inject
    ConsumptionDataResource resource;


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
    public void testShowTotalConsumption() throws Exception {

        String paramBoxID = "1";
        Integer in = resource.showTotalConsumption(paramBoxID);
        assertNotNull(in);

    }

    @Test
    public void testShowConsumption() throws Exception {

    }


    @Test
    public void testDailyConsumptionFake() throws Exception {

        Date date = Timestamp.valueOf("2014-12-2 00:00:00");
        List<GraphDataTransfer> list = resource.dailyConsumption(date);
        assertNotNull(list);

    }

    @Test
    public void testMonthlyConsumptionFake() throws Exception {

        Date date = Timestamp.valueOf("2014-12-2 00:00:00");
        List<GraphDataTransfer> list = resource.monthlyConsumption(date);
        assertNotNull(list);

    }
    @Test
    public void testYearlyConsumptionFake() throws Exception {

        Date date = Timestamp.valueOf("2014-12-2 00:00:00");
        List<GraphDataTransfer> list = resource.yearlyConsumption(date);
        assertNotNull(list);

    }
}