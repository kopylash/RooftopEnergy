package nl.rooftopenergy.bionic.pojo.weather.info;

import java.io.Serializable;
import java.util.List;

/**
 * It describes weather forecast to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
public class Info implements Serializable {
    private Long dt;
    private Rain rain;
    private String dt_txt;
    private Snow snow;
    private List<WeatherInfo> weather;
    private MainInfo main;
    private Clouds clouds;
    private Wind wind;
    private InfoSys sys;

    public Info(){}

    public Info(Long dt, Rain rain, String dt_txt, Snow snow, List<WeatherInfo> weather, MainInfo main, Clouds clouds, Wind wind, InfoSys sys) {
        this.dt = dt;
        this.rain = rain;
        this.dt_txt = dt_txt;
        this.snow = snow;
        this.weather = weather;
        this.main = main;
        this.clouds = clouds;
        this.wind = wind;
        this.sys = sys;
    }

    /**
     * Gets time, unix time, GMT
     */
    public Long getDt() {
        return dt;
    }

    /**
     * Sets time, unix time, GMT
     * @param dt time.
     */
    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    /**
     * Gets Date/time in UTC
     * @return date/time
     */
    public String getDt_txt() {
        return dt_txt;
    }

    /**
     * Sets Date/time in UTC
     * @param dt_txt date/time
     */
    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }

    public MainInfo getMain() {
        return main;
    }

    public void setMain(MainInfo main) {
        this.main = main;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public InfoSys getSys() {
        return sys;
    }

    public void setSys(InfoSys sys) {
        this.sys = sys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Info info = (Info) o;

        if (clouds != null ? !clouds.equals(info.clouds) : info.clouds != null) return false;
        if (dt != null ? !dt.equals(info.dt) : info.dt != null) return false;
        if (dt_txt != null ? !dt_txt.equals(info.dt_txt) : info.dt_txt != null) return false;
        if (main != null ? !main.equals(info.main) : info.main != null) return false;
        if (rain != null ? !rain.equals(info.rain) : info.rain != null) return false;
        if (snow != null ? !snow.equals(info.snow) : info.snow != null) return false;
        if (sys != null ? !sys.equals(info.sys) : info.sys != null) return false;
        if (weather != null ? !weather.equals(info.weather) : info.weather != null) return false;
        if (wind != null ? !wind.equals(info.wind) : info.wind != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dt != null ? dt.hashCode() : 0;
        result = 31 * result + (rain != null ? rain.hashCode() : 0);
        result = 31 * result + (dt_txt != null ? dt_txt.hashCode() : 0);
        result = 31 * result + (snow != null ? snow.hashCode() : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        result = 31 * result + (wind != null ? wind.hashCode() : 0);
        result = 31 * result + (sys != null ? sys.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Info{" +
                "dt=" + dt +
                ", rain=" + rain +
                ", dt_txt='" + dt_txt + '\'' +
                ", snow=" + snow +
                ", weather=" + weather +
                ", main=" + main +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", sys=" + sys +
                '}';
    }
}
