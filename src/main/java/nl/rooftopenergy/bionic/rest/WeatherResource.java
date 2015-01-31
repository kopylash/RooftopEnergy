package nl.rooftopenergy.bionic.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.rooftopenergy.bionic.pojo.weather.WeatherForecastActualDay;
import nl.rooftopenergy.bionic.pojo.weather.WeatherForecastForFiveDays;
import nl.rooftopenergy.bionic.pojo.weather.WeatherForecastForSixteenDays;
import nl.rooftopenergy.bionic.pojo.weather.info.Info;
import nl.rooftopenergy.bionic.pojo.weather.info.Precipitation;
import nl.rooftopenergy.bionic.pojo.weather.info.TemperatureInfo;
import nl.rooftopenergy.bionic.rest.util.PrincipalInformation;
import nl.rooftopenergy.bionic.transfer.WeatherActualDataTransfer;
import nl.rooftopenergy.bionic.transfer.WeatherCloudsTransfer;
import nl.rooftopenergy.bionic.transfer.WeatherDailyDataTransfer;
import nl.rooftopenergy.bionic.transfer.WeatherFiveDaysDataTransfer;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.util.*;

/**
 * Created by Alex Iakovenko on 12/18/14.
 */
@RestController
@Path("weather")
public class WeatherResource {
    private static final Logger logger = Logger.getLogger(WeatherResource.class.getName());
    private static final String DAILY_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q=";
    private static final String FIVE_DAYS_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String ACTUAL_DAY_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static final int MAX_DAYS_FORECAST = 14;
    private static final double ZERO_IN_KELVIN = 273.15;

    @Inject
    private PrincipalInformation principalInformation;


    @POST
    @Path("daily")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherDailyDataTransfer> showDailyWeather(@FormParam("days") String days){
        String thisCity = principalInformation.getCompany().getTown();
        Integer thisDays = Integer.parseInt(days);
        List<WeatherDailyDataTransfer> resultList = null;
        if ( thisDays == null || thisDays <= 0 || thisDays > MAX_DAYS_FORECAST){
            thisDays = MAX_DAYS_FORECAST;
        }
        try {
            HttpResponse<JsonNode> response1 = Unirest.get( DAILY_URL + thisCity + "&cnt=" + thisDays + "&mode=json").asJson();
            String json = response1.getBody().toString();
           /* Writer out = new OutputStreamWriter(new FileOutputStream(new File("/home/alex/sixteen.json")));
            out.write(json);
            out.close();*/
            ObjectMapper mapper = new ObjectMapper();
            WeatherForecastForSixteenDays weather = mapper.readValue(json, WeatherForecastForSixteenDays.class);

//            WeatherForecastForSixteenDays weather = mapper.readValue(new FileInputStream(new File("/home/alex/sixteen.json")), WeatherForecastForSixteenDays.class);

            WeatherDailyDataTransfer dataTransfer;
            List<TemperatureInfo> weatherInfoList = weather.getList();
            resultList = new ArrayList<WeatherDailyDataTransfer>();
            for (TemperatureInfo obj : weatherInfoList){
               dataTransfer = getForecast(obj);
                resultList.add(dataTransfer);

            }
        } catch (UnirestException e){
            logger.warn(e.getMessage(), e);
        } catch (JsonGenerationException e) {
            logger.warn(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.warn(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return resultList;
    }

    @POST
    @Path("fiveDay")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherFiveDaysDataTransfer> showFiveDayWeather(){
        List<WeatherFiveDaysDataTransfer> resultList = null;
        String thisCity = principalInformation.getCompany().getTown();
        try {
            HttpResponse<JsonNode> response1 = Unirest.get(FIVE_DAYS_URL + thisCity + "&mode=json").asJson();
            String json = response1.getBody().toString();
//            Writer out = new OutputStreamWriter(new FileOutputStream(new File("/home/alex/fiveDays.json")));
//            out.write(json);
//            out.close();
            ObjectMapper mapper = new ObjectMapper();

            WeatherForecastForFiveDays weather = mapper.readValue(json, WeatherForecastForFiveDays.class);

//            WeatherForecastForFiveDays weather = mapper.readValue(new FileInputStream(new File("/home/alex/fiveDays.json")), WeatherForecastForFiveDays.class);

            WeatherFiveDaysDataTransfer dataTransfer;
            List<Info> weatherInfoList = weather.getList();
            resultList = new ArrayList<WeatherFiveDaysDataTransfer>();
            for (Info obj : weatherInfoList){
                dataTransfer = getForecastFiveDays(obj);
                resultList.add(dataTransfer);

            }
        } catch (UnirestException e){
            logger.warn(e.getMessage(), e);
        } catch (JsonGenerationException e) {
            logger.warn(e.getMessage(), e);
        } catch (JsonMappingException e) {
            logger.warn(e.getMessage(), e);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return resultList;

    }

    @POST
    @Path("actualDay")
    @Produces(MediaType.APPLICATION_JSON)
    public WeatherActualDataTransfer showActualDayWeather(){
        String thisCity = principalInformation.getCompany().getTown();
        WeatherActualDataTransfer result = null;
        try {
            HttpResponse<JsonNode> response1 = Unirest.get( ACTUAL_DAY_URL + thisCity + "&mode=json").asJson();
            String json = response1.getBody().toString();
//            Writer out = new OutputStreamWriter(new FileOutputStream(new File("/home/alex/actual.json")));
//            out.write(json);
//            out.close();
            ObjectMapper mapper = new ObjectMapper();
            WeatherForecastActualDay weather = mapper.readValue(json, WeatherForecastActualDay.class);
//            ObjectMapper mapper = new ObjectMapper();
//            WeatherForecastActualDay weather = mapper.readValue(new FileInputStream(new File("/home/alex/actual.json")), WeatherForecastActualDay.class);

            result = getActualForecast(weather);

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

    @POST
    @Path("cloudsFiveDays")
    @Produces(MediaType.APPLICATION_JSON)
    public List<WeatherCloudsTransfer> showCloudsForFiveDays(){
        List<WeatherCloudsTransfer> resultList = null;
        String thisCity = principalInformation.getCompany().getTown();
        try {
            HttpResponse<JsonNode> response1 = Unirest.get(FIVE_DAYS_URL + thisCity + "&mode=json").asJson();
            String json = response1.getBody().toString();
//            Writer out = new OutputStreamWriter(new FileOutputStream(new File("/home/alex/fiveDays.json")));
//            out.write(json);
//            out.close();
            ObjectMapper mapper = new ObjectMapper();

            WeatherForecastForFiveDays weather = mapper.readValue(json, WeatherForecastForFiveDays.class);

//            WeatherForecastForFiveDays weather = mapper.readValue(new FileInputStream(new File("/home/alex/fiveDays.json")), WeatherForecastForFiveDays.class);

            WeatherCloudsTransfer dataTransfer;
            List<Info> weatherInfoList = weather.getList();
            resultList = new ArrayList<WeatherCloudsTransfer>();
            for (Info obj : weatherInfoList){
                dataTransfer = getCloudsForecast(obj);
                resultList.add(dataTransfer);

            }
        } catch (UnirestException e){
            logger.warn(e.getMessage(), e);
            throw new WebApplicationException(503);
        } catch (JsonGenerationException e) {
            logger.warn(e.getMessage(), e);
            throw new WebApplicationException(503);
        } catch (JsonMappingException e) {
            logger.warn(e.getMessage(), e);
            throw new WebApplicationException(503);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            throw new WebApplicationException(503);
        } catch (NullPointerException e){
            logger.error(e.getMessage(), e);
            throw new WebApplicationException(503);
        }
        return resultList;

    }

    private WeatherDailyDataTransfer getForecast(TemperatureInfo info){

        WeatherDailyDataTransfer dataTransfer = new WeatherDailyDataTransfer();
        dataTransfer.setDt(info.getDt() * 1000); //This parameter does not include milliseconds in JSON
        dataTransfer.setTempDay(info.getTemp().getDay() - ZERO_IN_KELVIN);
        dataTransfer.setTempNight(info.getTemp().getNight() - ZERO_IN_KELVIN);
        dataTransfer.setPressure(info.getPressure());
        dataTransfer.setHumidity(info.getHumidity());
        dataTransfer.setSky(info.getWeather().get(0).getMain());
        dataTransfer.setSkyDescription(info.getWeather().get(0).getDescription());
        dataTransfer.setSkyIcon(info.getWeather().get(0).getIcon());
        dataTransfer.setWind(info.getSpeed());
        dataTransfer.setClouds(info.getClouds());
        dataTransfer.setTemperatureDay(info.getTemp().getDay()-ZERO_IN_KELVIN);
        dataTransfer.setTemperatureMorning(info.getTemp().getMorn()-ZERO_IN_KELVIN);
        dataTransfer.setTemperatureEvening(info.getTemp().getEve()-ZERO_IN_KELVIN);
        dataTransfer.setTemperatureNight(info.getTemp().getNight()-ZERO_IN_KELVIN);
        return dataTransfer;

    }
    private WeatherFiveDaysDataTransfer getForecastFiveDays(Info info){

        WeatherFiveDaysDataTransfer dataTransfer = new WeatherFiveDaysDataTransfer();
        dataTransfer.setDt(info.getDt() * 1000);//This parameter does not include milliseconds in JSON
        dataTransfer.setDateText(info.getDt_txt());

        Precipitation rain = info.getRain();
        if (rain != null) {
            dataTransfer.setRain(rain.get_3h());
        } else {
            dataTransfer.setRain(null);
        }

        Precipitation snow = info.getSnow();
        if (snow != null) {
            dataTransfer.setSnow(snow.get_3h());
        } else {
            dataTransfer.setSnow(null);
        }

        dataTransfer.setTempDay(info.getMain().getTemp_max() - ZERO_IN_KELVIN);
        dataTransfer.setTempNight(info.getMain().getTemp_min() - ZERO_IN_KELVIN);
        dataTransfer.setPressure(info.getMain().getPressure());
        dataTransfer.setHumidity(info.getMain().getHumidity());
        dataTransfer.setSky(info.getWeather().get(0).getMain());
        dataTransfer.setSkyDescription(info.getWeather().get(0).getDescription());
        dataTransfer.setSkyIcon(info.getWeather().get(0).getIcon());
        dataTransfer.setClouds(info.getClouds().getAll());
        return dataTransfer;

    }

    private WeatherActualDataTransfer getActualForecast(WeatherForecastActualDay weather){

        WeatherActualDataTransfer dataTransfer = new WeatherActualDataTransfer();
        dataTransfer.setDt(weather.getDt() * 1000); //This parameter does not include milliseconds in JSON
        dataTransfer.setSunrise(weather.getSys().getSunrise() * 1000);//This parameter does not include milliseconds in JSON
        dataTransfer.setSunset(weather.getSys().getSunset() *1000);//This parameter does not include milliseconds in JSON
        dataTransfer.setSpeed(weather.getWind().getSpeed());
        dataTransfer.setDeg(weather.getWind().getDeg());
        dataTransfer.setDescription(weather.getWeather().get(0).getDescription());
        return dataTransfer;

    }

    private WeatherCloudsTransfer getCloudsForecast(Info info){
        WeatherCloudsTransfer dataTransfer = new WeatherCloudsTransfer();
        dataTransfer.setClouds(info.getClouds().getAll());
        dataTransfer.setDt(info.getDt() * 1000);
        return dataTransfer;
    }

}
