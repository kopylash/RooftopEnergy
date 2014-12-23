package nl.rooftopenergy.bionic.pojo.weather.city;

import java.io.Serializable;

/**
 * Class describes city to parse weather forecast JSON file from
 * http://openweathermap.org.
 * Created by Alex Iakovenko on 12/18/14.
 */
public class City implements Serializable {

    private Integer id;
    private String name;
    private Integer population;
    private String country;
    private Coordinates coord;
    private Sys sys;

    public City(){}

    public City(Integer id, String name, Integer population, String country, Coordinates coord, Sys sys) {
        this.id = id;
        this.name = name;
        this.population = population;
        this.country = country;
        this.coord = coord;
        this.sys = sys;
    }

    /**
     * Gets city identification number
     * @return city identification number
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets city identification number
     * @param id identification number
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets city name
     * @return city name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets city name
     * @param name city name
     */
    public void setName(String name) {
        this.name = name;
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
     * Gets city geo location
     * @return city geo location
     */
    public Coordinates getCoord() {
        return coord;
    }

    /**
     * Sets city geo location
     * @param coord geo location
     */
    public void setCoord(Coordinates coord) {
        this.coord = coord;
    }


    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (coord != null ? !coord.equals(city.coord) : city.coord != null) return false;
        if (country != null ? !country.equals(city.country) : city.country != null) return false;
        if (id != null ? !id.equals(city.id) : city.id != null) return false;
        if (name != null ? !name.equals(city.name) : city.name != null) return false;
        if (population != null ? !population.equals(city.population) : city.population != null) return false;
        if (sys != null ? !sys.equals(city.sys) : city.sys != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (population != null ? population.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (coord != null ? coord.hashCode() : 0);
        result = 31 * result + (sys != null ? sys.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", population=" + population +
                ", country='" + country + '\'' +
                ", coord=" + coord +
                ", sys=" + sys +
                '}';
    }
}
