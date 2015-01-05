package nl.rooftopenergy.bionic.pojo.weather;

import nl.rooftopenergy.bionic.pojo.weather.city.Coordinates;
import nl.rooftopenergy.bionic.pojo.weather.city.Sys;
import nl.rooftopenergy.bionic.pojo.weather.info.*;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.util.List;

/**
 *  * It describes weather forecast for actual day to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by alex on 1/2/15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastActualDay {
    private Rain rain;
    private Coordinates coord;
    private Sys sys;
    private List<WeatherInfo> weather;
    private String base;
    private MainInfo main;
    private Wind wind;
    private Clouds clouds;
    private Long dt;
    private Integer id;
    private String name;
    private Integer cod;

    public WeatherForecastActualDay(){}

    public WeatherForecastActualDay(Rain rain, Coordinates coord, Sys sys, List<WeatherInfo> weather,
                                    String base, MainInfo main, Wind wind, Clouds clouds,
                                    Long dt, Integer id, String name, Integer cod) {
        this.rain = rain;
        this.coord = coord;
        this.sys = sys;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.id = id;
        this.name = name;
        this.cod = cod;
    }

    public Precipitation getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Coordinates getCoord() {
        return coord;
    }

    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }


}
