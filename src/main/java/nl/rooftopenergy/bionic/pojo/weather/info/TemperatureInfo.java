package nl.rooftopenergy.bionic.pojo.weather.info;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * * It describes weather forecast to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemperatureInfo implements Serializable {
    private Long dt;
    private Integer rain;
    private Temperature temp;
    private Double snow;
    private Double deg;
    private List<WeatherInfo> weather;
    private Integer humidity;
    private Double pressure;
    private Integer clouds;
    private Double speed;

    public TemperatureInfo(){}

    public TemperatureInfo(Long dt, Integer rain, Temperature temp, Double snow, Double deg, List<WeatherInfo> weather, Integer humidity, Double pressure, Integer clouds, Double speed) {
        this.dt = dt;
        this.rain = rain;
        this.temp = temp;
        this.snow = snow;
        this.deg = deg;
        this.weather = weather;
        this.humidity = humidity;
        this.pressure = pressure;
        this.clouds = clouds;
        this.speed = speed;
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

    public Integer getRain() {
        return rain;
    }

    public void setRain(Integer rain) {
        this.rain = rain;
    }

    /**
     * Gets temperature, Kelvin (subtract 273.15 to convert to Celsius)
     * @return temperature
     */
    public Temperature getTemp() {
        return temp;
    }

    /**
     * Sets temperature, Kelvin (subtract 273.15 to convert to Celsius)
     * @param temp temperature
     */
    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public Double getSnow() {
        return snow;
    }

    public void setSnow(Double snow) {
        this.snow = snow;
    }

    /**
     * Gets wind direction to be expressed in degrees (meteorological)
     * @return wind direction.
     */
    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public List<WeatherInfo> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherInfo> weather) {
        this.weather = weather;
    }

    /**
     * Gets Humidity, %*
     * @return Humidity, %
     */
    public Integer getHumidity() {
        return humidity;
    }

    /**
     * Sets Humidity, %*
     * @param humidity, %
     */
    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    /**
     * Gets Atmospheric pressure, hPa
     *
     * @return pressure atmospheric pressure on the sea level, hPa
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * Sets Atmospheric pressure, hPa
     *
     * @param pressure atmospheric pressure on the sea level, hPa
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets cloudiness to be expressed in percents.
     * @return cloudiness in %.
     */
    public Integer getClouds() {
        return clouds;
    }

    /**
     * Sets cloudiness to be expressed in percents.
     * @return cloudiness in %.
     */
    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    /**
     * Gets wind speed to be expressed in mps
     * @return wind speed
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Sets wind speed to be expressed in mps
     * @param speed
     */
    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TemperatureInfo that = (TemperatureInfo) o;

        if (clouds != null ? !clouds.equals(that.clouds) : that.clouds != null) return false;
        if (deg != null ? !deg.equals(that.deg) : that.deg != null) return false;
        if (dt != null ? !dt.equals(that.dt) : that.dt != null) return false;
        if (humidity != null ? !humidity.equals(that.humidity) : that.humidity != null) return false;
        if (pressure != null ? !pressure.equals(that.pressure) : that.pressure != null) return false;
        if (rain != null ? !rain.equals(that.rain) : that.rain != null) return false;
        if (snow != null ? !snow.equals(that.snow) : that.snow != null) return false;
        if (speed != null ? !speed.equals(that.speed) : that.speed != null) return false;
        if (temp != null ? !temp.equals(that.temp) : that.temp != null) return false;
        if (weather != null ? !weather.equals(that.weather) : that.weather != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dt != null ? dt.hashCode() : 0;
        result = 31 * result + (rain != null ? rain.hashCode() : 0);
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (snow != null ? snow.hashCode() : 0);
        result = 31 * result + (deg != null ? deg.hashCode() : 0);
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (clouds != null ? clouds.hashCode() : 0);
        result = 31 * result + (speed != null ? speed.hashCode() : 0);
        return result;
    }

}
