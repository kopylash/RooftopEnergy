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

    public Sys(){}

    public Sys(Integer population) {
        this.population = population;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sys sys = (Sys) o;

        if (population != null ? !population.equals(sys.population) : sys.population != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return population != null ? population.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Sys{" +
                "population=" + population +
                '}';
    }
}
