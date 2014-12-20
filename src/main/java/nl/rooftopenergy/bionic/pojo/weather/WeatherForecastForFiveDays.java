package nl.rooftopenergy.bionic.pojo.weather;

import nl.rooftopenergy.bionic.pojo.weather.city.City;
import nl.rooftopenergy.bionic.pojo.weather.info.Info;

import java.io.Serializable;
import java.util.List;

/**
 * It describes weather forecast for five days to parse JSON file
 * from  http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
public class WeatherForecastForFiveDays implements Serializable {
    private Double message;
    private Integer cnt;
    private Integer cod;
    private List<Info> list;
    private City city;

    public WeatherForecastForFiveDays(){}

    public WeatherForecastForFiveDays(Double message, Integer ctn, Integer cod, List<Info> list, City city) {
        this.message = message;
        this.cnt = ctn;
        this.cod = cod;
        this.list = list;
        this.city = city;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
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

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public List<Info> getList() {
        return list;
    }

    public void setList(List<Info> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherForecastForFiveDays that = (WeatherForecastForFiveDays) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (cod != null ? !cod.equals(that.cod) : that.cod != null) return false;
        if (cnt != null ? !cnt.equals(that.cnt) : that.cnt != null) return false;
        if (list != null ? !list.equals(that.list) : that.list != null) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = message != null ? message.hashCode() : 0;
        result = 31 * result + (cnt != null ? cnt.hashCode() : 0);
        result = 31 * result + (cod != null ? cod.hashCode() : 0);
        result = 31 * result + (list != null ? list.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherForecast{" +
                "message=" + message +
                ", cnt=" + cnt +
                ", cod=" + cod +
                ", list=" + list +
                ", city=" + city +
                '}';
    }
}
