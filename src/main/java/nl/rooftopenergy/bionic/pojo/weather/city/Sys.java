package nl.rooftopenergy.bionic.pojo.weather.city;

import java.io.Serializable;

/**
 * Class describes System information about the city such as population.
 * It is used for parsing forecast JSON file from http://openweathermap.org.
 *
 * Created by Alex Iakovenko on 12/18/14.
 */
public class Sys implements Serializable {
    private Integer population;
    private Double message;
    private String country;
    private Long sunrise;
    private Long sunset;
    private Integer id;
    private Integer type;

    public Sys(){}

    public Sys(Integer population, Double message, String country, Long sunrise, Long sunset, Integer id, Integer type) {
        this.population = population;
        this.message = message;
        this.country = country;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.id = id;
        this.id = type;
    }

    /**
     *Gets city population
     * @return population
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     *Sets city population
     * @param population
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    /**
     *Gets system parameter (do not use it)
     * @return system parameter
     */
    public Double getMessage() {
        return message;
    }
    /**
     *Sets system parameter (do not use it)
     * @param message system parameter
     */
    public void setMessage(Double message) {
        this.message = message;
    }

    /**
     * Gets country (GB, JP etc.)
     * @return country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country (GB, JP etc.)
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets sunrise time, unix, UTC
     * @return sunrise time
     */
    public Long getSunrise() {
        return sunrise;
    }

    /**
     * Sets sunrise time, unix, UTC
     * @param sunrise time
     */
    public void setSunrise(Long sunrise) {
        this.sunrise = sunrise;
    }

    /**
     * Gets sunrise time, unix, UTC
     * @return sunset time
     */
    public Long getSunset() {
        return sunset;
    }

    /**
     * Sets sunset time, unix, UTC
     * @param sunset time
     */
    public void setSunset(Long sunset) {
        this.sunset = sunset;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (country != null ? !country.equals(sys.country) : sys.country != null) return false;
        if (id != null ? !id.equals(sys.id) : sys.id != null) return false;
        if (message != null ? !message.equals(sys.message) : sys.message != null) return false;
        if (population != null ? !population.equals(sys.population) : sys.population != null) return false;
        if (sunrise != null ? !sunrise.equals(sys.sunrise) : sys.sunrise != null) return false;
        if (sunset != null ? !sunset.equals(sys.sunset) : sys.sunset != null) return false;
        if (type != null ? !type.equals(sys.type) : sys.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = population != null ? population.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (sunrise != null ? sunrise.hashCode() : 0);
        result = 31 * result + (sunset != null ? sunset.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "population=" + population +
                ", message=" + message +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                ", id=" + id +
                ", type=" + type +
                '}';
    }
}
