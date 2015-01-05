package nl.rooftopenergy.bionic.pojo.weather;

import nl.rooftopenergy.bionic.pojo.weather.city.City;
import nl.rooftopenergy.bionic.pojo.weather.info.TemperatureInfo;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

/**
 * It describes weather forecast for sixteen days to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherForecastForSixteenDays implements Serializable {
    private Integer cod;
    private Double message;
    private City city;
    private Integer cnt;
    private List<TemperatureInfo> list;

    public WeatherForecastForSixteenDays(){}

    public WeatherForecastForSixteenDays(Integer cod, Double message, City city,
                                         Integer cnt, List<TemperatureInfo> list) {
        this.cod = cod;
        this.message = message;
        this.city = city;
        this.cnt = cnt;
        this.list = list;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    /**
     * Gets number of lines returned by API call
     * @return number of lines
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * Sets number of lines returned for API call
     * @param cnt number of lines
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public List<TemperatureInfo> getList() {
        return list;
    }

    public void setList(List<TemperatureInfo> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherForecastForSixteenDays that = (WeatherForecastForSixteenDays) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (cnt != null ? !cnt.equals(that.cnt) : that.cnt != null) return false;
        if (cod != null ? !cod.equals(that.cod) : that.cod != null) return false;
        if (list != null ? !list.equals(that.list) : that.list != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cod != null ? cod.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (cnt != null ? cnt.hashCode() : 0);
        result = 31 * result + (list != null ? list.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherForecastForSixteenDays{" +
                "cod=" + cod +
                ", message=" + message +
                ", city=" + city +
                ", cnt=" + cnt +
                ", list=" + list +
                '}';
    }
}
