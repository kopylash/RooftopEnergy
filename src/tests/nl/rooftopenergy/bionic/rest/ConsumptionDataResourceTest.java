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
//        Authentication auth = new UsernamePasswordAuthenticationToken("rooftop", "energy");
        Authentication auth = new UsernamePasswordAuthenticationToken("target", "qwerty");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
    }
    @Test
    public void testShowTotalConsumption() throws Exception {

        Integer in = resource.showTotalConsumption();
        assertNotNull(in);

    }


    @Test
    public void testDailyConsumption() throws Exception {

        Date date = Timestamp.valueOf("2014-12-2 00:00:00");
        List<GraphDataTransfer> list = resource.dailyConsumption(String.valueOf(date.getTime()));
        assertNotNull(list);

    }

    @Test
    public void testMonthlyConsumption() throws Exception {

        Date date = Timestamp.valueOf("2014-12-2 00:00:00");
        List<GraphDataTransfer> list = resource.monthlyConsumption(String.valueOf(date.getTime()));
        assertNotNull(list);

    }
    @Test
    public void testYearlyConsumption() throws Exception {

        Date date = Timestamp.valueOf("2014-12-2 00:00:00");
        List<GraphDataTransfer> list = resource.yearlyConsumption(String.valueOf(date.getTime()));
        assertNotNull(list);

    }

    @Test
    public void testThisDayTotalConsumption() throws Exception{
        Integer dailyConsumption = resource.thisDayTotalConsumption();
        assertNotNull(dailyConsumption);
    }

    @Test
    public void testThisMonthTotalConsumption() throws Exception{
        Integer monthlyConsumption = resource.thisMonthTotalConsumption();
        assertNotNull(monthlyConsumption);
    }

    @Test
    public void testThisYearTotalConsumption() throws Exception{
        Integer yearlyConsumption = resource.thisYearTotalConsumption();
        assertNotNull(yearlyConsumption);
    }
}