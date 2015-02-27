package nl.rooftopenergy.bionic.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.rooftopenergy.bionic.entity.Company;
import nl.rooftopenergy.bionic.pojo.weather.WeatherForecastActualDay;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import nl.rooftopenergy.bionic.rest.util.Solar;
import nl.rooftopenergy.bionic.transfer.RadiationTransfer;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Provides information about amount of energy the panel could produce
 * depends on geographical location (latitude) of the a city and the panel tilt.
 * The latitude is got from www.openweathermap.org by city mane.
 *
 * Created by Alexander Iakovenko on 2/24/15.
 */
@RestController
@Path("radiation")
public class RadiationResource {
    private static final Logger logger = Logger.getLogger(RadiationResource.class);
    private static final String URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final double PANEL_TILT = 45.0; //It means the panel has angle 45 degrees to horizontal.

    @Inject
    private PrincipalInformation principalInformation;


    @POST
    @Path("year")
    @Produces(MediaType.APPLICATION_JSON)
    public RadiationTransfer radiationForYear(@FormParam("tilt") String tilt){
        final int FULL_DAY = 365;
        Double angle;
        if (tilt != null){
            angle = Double.parseDouble(tilt);
        } else {
            angle = PANEL_TILT;
        }
        Company company = principalInformation.getCompany();
        String city = company.getTown();
        Double latitude = getLatitude(city);
        Solar solar = new Solar(latitude, angle, FULL_DAY);
        RadiationTransfer result = new RadiationTransfer(latitude, angle);
        result.setData(solar.calculateTotal());
        return result;
    }

    @POST
    @Path("day")
    @Produces(MediaType.APPLICATION_JSON)
    public RadiationTransfer radiationForDay(@FormParam("tilt") String tilt, @FormParam("day") String paramDay){
        RadiationTransfer result = null;
        if (paramDay != null) {
            Double angle;
            if (tilt != null) {
                angle = Double.parseDouble(tilt);
            } else {
                angle = PANEL_TILT;
            }
            Integer day = Integer.parseInt(paramDay);

            Company company = principalInformation.getCompany();
            String city = company.getTown();
            Double latitude = getLatitude(city);
            Solar solar = new Solar(latitude, angle, null);
            solar.setDay(day);
            result = new RadiationTransfer(latitude, angle);
            result.setData(solar.calculateOneDay());
        }
        return result;
    }

    private double getLatitude(String city){
        Double result = null;
        try {
            HttpResponse<JsonNode> response1 = Unirest.get(URL+city).asJson();
            String json = response1.getBody().toString();
            ObjectMapper mapper = new ObjectMapper();
            WeatherForecastActualDay weather = mapper.readValue(json, WeatherForecastActualDay.class);

            result = weather.getCoord().getLat();
        } catch (UnirestException e){
            logger.warn(e.getMessage(), e);
        } catch (JsonGenerationException e) {
            logger.warn(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.warn(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }

        return result;
    }


}
