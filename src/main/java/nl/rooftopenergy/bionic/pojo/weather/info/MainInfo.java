package nl.rooftopenergy.bionic.pojo.weather.info;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * It describes weather forecast to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainInfo implements Serializable {
    private Double temp_kf;
    private Integer humidity;
    private Double pressure;
    private Double temp_max;
    private Double sea_level;
    private Double temp_min;
    private Double temp;
    private Double grnd_level;

    public MainInfo(){}

    public MainInfo(Double temp_kf, Integer humidity, Double pressure, Double temp_max, Double sea_level, Double temp_min, Double temp, Double grnd_level) {
        this.temp_kf = temp_kf;
        this.humidity = humidity;
        this.pressure = pressure;
        this.temp_max = temp_max;
        this.sea_level = sea_level;
        this.temp_min = temp_min;
        this.temp = temp;
        this.grnd_level = grnd_level;
    }


    public Double getTemp_kf() {
        return temp_kf;
    }

    public void setTemp_kf(Double temp_kf) {
        this.temp_kf = temp_kf;
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
     * Gets Atmospheric pressure (on the sea level, if there is no sea_level or
     * grnd_level data), hPa
     *
     * @return pressure atmospheric pressure on the sea level, hPa
     */
    public Double getPressure() {
        return pressure;
    }

    /**
     * Sets Atmospheric pressure (on the sea level, if there is no sea_level or
     * grnd_level data), hPa
     *
     * @param pressure atmospheric pressure on the sea level, hPa
     */
    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    /**
     * Gets maximum temperature at the moment.
     * This is deviation from current temp that is possible for large cities and
     * megalopolises geographically expanded (use these parameter optionally)
     *
     * @return atmospheric pressure on the sea level
     */
    public Double getTemp_max() {
        return temp_max;
    }

    /**
     * Sets maximum temperature at the moment.
     * This is deviation from current temp that is possible for large cities and
     * megalopolises geographically expanded (use these parameter optionally)
     *
     * @param temp_max atmospheric pressure on the sea level
     */
    public void setTemp_max(Double temp_max) {
        this.temp_max = temp_max;
    }

    /**
     * Gets atmospheric pressure on the sea level, hPa
     * @return atmospheric pressure on the sea level
     */
    public Double getSea_level() {
        return sea_level;
    }

    /**
     * Sets atmospheric pressure on the sea level, hPa
     * @param sea_level atmospheric pressure on the sea level, hPa
     */
    public void setSea_level(Double sea_level) {
        this.sea_level = sea_level;
    }

    /**
     * Gets minimum temperature at the moment. This is deviation from current temp that
     * is possible for large cities and megalopolises geographically expanded (use
     * these parameter optionally)
     *
     * @return temp_min atmospheric pressure on the sea level
     */
    public Double getTemp_min() {
        return temp_min;
    }

    /**
     * Sets minimum temperature at the moment. This is deviation from current temp that
     * is possible for large cities and megalopolises geographically expanded (use
     * these parameter optionally)
     *
     * @param temp_min atmospheric pressure on the sea level
     */
    public void setTemp_min(Double temp_min) {
        this.temp_min = temp_min;
    }

    /**
     * Gets temperature, Kelvin (subtract 273.15 to convert to Celsius)
     * @return temperature
     */
    public Double getTemp() {
        return temp;
    }

    /**
     * Sets temperature, Kelvin (subtract 273.15 to convert to Celsius)
     * @param temp temperature
     */
    public void setTemp(Double temp) {
        this.temp = temp;
    }

    /**
     * Gets atmospheric pressure on the ground level, hPa
     * @return atmospheric pressure on the ground level
     */
    public Double getGrnd_level() {
        return grnd_level;
    }

    /**
     * Sets atmospheric pressure on the ground level, hPa
     * @param grnd_level atmospheric pressure on the ground level
     */
    public void setGrnd_level(Double grnd_level) {
        this.grnd_level = grnd_level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainInfo mainInfo = (MainInfo) o;

        if (grnd_level != null ? !grnd_level.equals(mainInfo.grnd_level) : mainInfo.grnd_level != null) return false;
        if (humidity != null ? !humidity.equals(mainInfo.humidity) : mainInfo.humidity != null) return false;
        if (pressure != null ? !pressure.equals(mainInfo.pressure) : mainInfo.pressure != null) return false;
        if (sea_level != null ? !sea_level.equals(mainInfo.sea_level) : mainInfo.sea_level != null) return false;
        if (temp != null ? !temp.equals(mainInfo.temp) : mainInfo.temp != null) return false;
        if (temp_kf != null ? !temp_kf.equals(mainInfo.temp_kf) : mainInfo.temp_kf != null) return false;
        if (temp_max != null ? !temp_max.equals(mainInfo.temp_max) : mainInfo.temp_max != null) return false;
        if (temp_min != null ? !temp_min.equals(mainInfo.temp_min) : mainInfo.temp_min != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = temp_kf != null ? temp_kf.hashCode() : 0;
        result = 31 * result + (humidity != null ? humidity.hashCode() : 0);
        result = 31 * result + (pressure != null ? pressure.hashCode() : 0);
        result = 31 * result + (temp_max != null ? temp_max.hashCode() : 0);
        result = 31 * result + (sea_level != null ? sea_level.hashCode() : 0);
        result = 31 * result + (temp_min != null ? temp_min.hashCode() : 0);
        result = 31 * result + (temp != null ? temp.hashCode() : 0);
        result = 31 * result + (grnd_level != null ? grnd_level.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MainInfo{" +
                "temp_kf=" + temp_kf +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                ", temp_max=" + temp_max +
                ", sea_level=" + sea_level +
                ", temp_min=" + temp_min +
                ", temp=" + temp +
                ", grnd_level=" + grnd_level +
                '}';
    }
}
