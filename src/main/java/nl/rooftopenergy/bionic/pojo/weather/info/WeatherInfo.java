package nl.rooftopenergy.bionic.pojo.weather.info;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * It describes weather condition to parse JSON file
 * from  http://openweathermap.org.
 * May visit <a href='http://openweathermap.org/weather-conditions'/> In order
 * to get more information about Weater Condition Codes
 *
 * Created by alex on 12/18/14.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherInfo implements Serializable {
    private Integer id;
    private String icon;
    private String description;
    private String main;

    public WeatherInfo(){}

    public WeatherInfo(Integer id, String icon, String description, String main) {
        this.id = id;
        this.icon = icon;
        this.description = description;
        this.main = main;
    }

    /**
     * Gets weather condition id
     * @return weather condition id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets weather condition id
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets weather icon name
     * @return weather icon id
     */
    public String getIcon() {
        return icon;
    }

    /**
     * Gets weather icon name
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * Gets weather condition within the group
     * @return weather condition within the group
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets weather condition within the group
     * @param description weather condition within the group
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets group of weather parameters (Rain, Snow, Extreme etc.)
     * @return group
     */
    public String getMain() {
        return main;
    }

    /**
     * Sets group of weather parameters (Rain, Snow, Extreme etc.)
     * @param main name of group
     */
    public void setMain(String main) {
        this.main = main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherInfo that = (WeatherInfo) o;

        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (main != null ? !main.equals(that.main) : that.main != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (main != null ? main.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "id=" + id +
                ", icon='" + icon + '\'' +
                ", description='" + description + '\'' +
                ", main='" + main + '\'' +
                '}';
    }
}
