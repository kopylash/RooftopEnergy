package nl.rooftopenergy.bionic.rest;

import nl.rooftopenergy.bionic.rest.util.MapEntity;
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

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContextForUnitTests.xml"})
public class MapDataResourceTest {

    @Inject
    private MapDataResource mapDataResource;

    @BeforeClass
    public static void setup() {
        Authentication auth = new UsernamePasswordAuthenticationToken("target", "qwerty");
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
    }

    @Test
    public void testProductionMonthly() throws Exception {
        List<MapEntity> list = mapDataResource.productionMonthly();
        double result = 0d;
        for (MapEntity e : list){
            Double val = e.getValue();
            result += val != null ? val : 0d ;
        }
        assertTrue(result > 0);

    }

    @Test
    public void testProductionYearly() throws Exception {
        List<MapEntity> list = mapDataResource.productionYearly();
        double result = 0d;
        for (MapEntity e : list){
            Double val = e.getValue();
            result += val != null ? val : 0d ;
        }
        assertTrue(result > 0);

    }

    @Test
    public void testConsumptionMonthly() throws Exception {
        List<MapEntity> list = mapDataResource.consumptionMonthly();
        double result = 0d;
        for (MapEntity e : list){
            Double val = e.getValue();
            result += val != null ? val : 0d ;
        }
        assertTrue(result > 0);
    }

    @Test
    public void testConsumptionYearly() throws Exception {
        List<MapEntity> list = mapDataResource.consumptionYearly();
        double result = 0d;
        for (MapEntity e : list){
            Double val = e.getValue();
            result += val != null ? val : 0d ;
        }
        assertTrue(result > 0);

    }

    @Test
    public void testRatioMonthly() throws Exception {
        List<MapEntity> list = mapDataResource.ratioMonthly();
        double result = 0d;
        for (MapEntity e : list){
            Double val = e.getValue();
            result += val != null ? val : 0d ;
        }
        assertTrue(result > 0);

    }

    @Test
    public void testRatioYearly() throws Exception {
        List<MapEntity> list = mapDataResource.ratioYearly();
        double result = 0d;
        for (MapEntity e : list){
            Double val = e.getValue();
            result += val != null ? val : 0d ;
        }
        assertTrue(result > 0);

    }
}